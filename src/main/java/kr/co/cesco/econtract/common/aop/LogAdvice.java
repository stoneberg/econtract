package kr.co.cesco.econtract.common.aop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.co.cesco.econtract.config.security.userdetails.CustomUserDetails;
import kr.co.cesco.econtract.web.users.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * LogAdvice
 */
@Aspect
@Component
public class LogAdvice {

    private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);

    /**
     * Skip Request, Response list
     */
    private static final List<String> SKIP_ARGS = Collections.unmodifiableList(Arrays.asList(
            "HttpServletRequest",
            "HttpServletResponse",
            "RequestBodyWrapper",
            "ResponseBodyWrapper",
            "HeaderWriterRequest",
            "HeaderWriterResponse",
            "XssEscapeServletFilterWrapper",
            "ResourceUrlEncodingResponseWrapper",
            "ResourceUrlEncodingRequestWrapper"
    ));

    /**
     * InLogExclusion 선언 시 로깅에서 제외
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* kr.co.cesco.econtract.*.*.controller.*.*(..)) "
    		+ "&& !@annotation(kr.co.cesco.econtract.common.aop.InLogExclusion)")
    public Object controllerLog(ProceedingJoinPoint pjp) throws Throwable {
        String currentUser = this.currentUser();
        log.info("#################################################################################");
        log.info("@[TraceLog : Start] - {}/{}/{}", currentUser, pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        long startTime = System.currentTimeMillis();

        Object[] signatureArgs = pjp.getArgs();
        for (Object signatureArg : signatureArgs) {
            this.printJsonFormatParamLog(pjp, signatureArg);
        }

        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("@{} took {} seconds", pjp.getSignature().getName(), TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));
        log.info("@[TraceLog : End] - {}/{}/{}", currentUser, pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        log.info("#################################################################################");
        return result;
    }

    /**
     * print controller, service method params in json format
     *
     * @param pjp, arg
     */
    public void printJsonFormatParamLog(ProceedingJoinPoint pjp, Object arg) {
        for (String skipArg : SKIP_ARGS) {
            if (arg.toString().contains(skipArg)) {
                return;
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        log.info("\r\n@Request [{}]'s params are\r\n ### {} ###", pjp.getSignature().getName(),
                !StringUtils.isBlank(gson.toJson(arg)) ? gson.toJson(arg) : "Empty");
    }

    /**
     * success logging
     * OutLogInclusion 선언시 사용가능
     *
     * @param jp
     * @param result
     */
    @AfterReturning(value = "execution(* kr.co.cesco.econtract.*.*.controller.*.*(..)) " +
            "&& @annotation(kr.co.cesco.econtract.common.aop.OutLogInclusion))", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        log.info("@{} returned with value {}", jp, result);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        log.info("@[{}]'s result are\r\n # {} #", jp.getSignature().getName(),
                !StringUtils.isBlank(gson.toJson(result)) ? gson.toJson(result) : "Empty");
    }
    
    /**
     * Api Error Log
     *
     * @param jp
     * @param ex
     */
    @AfterThrowing(value = "execution(* kr.co.cesco.econtract.*.*.controller.*.*(..))", throwing = "ex")
    public void error(JoinPoint jp, Exception ex) {
        String currentUser = this.currentUser();
    	log.error("#################################################################################");
        log.error("@[ErrorLog : Start] - {}/{}/{}", currentUser, jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        Object[] signatureArgs = jp.getArgs();

        for (Object signatureArg : signatureArgs) {
            if (StringUtils.isNotBlank(jp.getSignature().getName())) {
                this.printJsonFormatParamErrorLog(jp, signatureArg);
            }
        }

        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("@[ErrorLog : End] - {}/{}/{}", currentUser, jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        log.error("#################################################################################");
    }
    
    
    /**
     * print controller, service method params in json format
     *
     * @param arg
     */
    public void printJsonFormatParamErrorLog(JoinPoint jp, Object arg) {
        for (String skipArg : SKIP_ARGS) {
            if (arg.toString().contains(skipArg)) {
                return;
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        log.error("\r\n@Request [{}]'s params are\r\n ### {} ###", jp.getSignature().getName(),
                !StringUtils.isBlank(gson.toJson(arg)) ? gson.toJson(arg) : "Empty");
    }

    private String currentUser() {
        String username = "";
        String fullname = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            username = user.getUsername();
            fullname = user.getFullname();
        }

        return username + "/" + fullname;
    }

}
