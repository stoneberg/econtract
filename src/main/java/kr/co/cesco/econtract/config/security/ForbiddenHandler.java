package kr.co.cesco.econtract.config.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ForbiddenHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(ForbiddenHandler.class);

    /**
     * 403 : 인증은 통과했지만 접근 권한 없음 -> 403 리다이렉트
     *
     * @param request
     * @param response
     * @param deniedException
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException deniedException)
            throws ServletException, IOException {
        log.error("▷▷▷ Forbidden error: {}", deniedException.getMessage());
        log.error("▷▷▷ Redirect to APP_403_URL======>");
        response.sendRedirect(request.getContextPath() + AuthConst.APP_403_URL);

    }

}
