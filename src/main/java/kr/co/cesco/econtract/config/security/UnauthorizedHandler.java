package kr.co.cesco.econtract.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {

	private static final Logger log = LoggerFactory.getLogger(UnauthorizedHandler.class);

	/**
	 * 
	 * 401 : 로그인 인증 통과 못해서 리소스 사용 권한 없음 -> 로그인 페이지로 포워드
	 * 
	 * @param request
	 * @param response
	 * @param authException
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws ServletException, IOException {
		log.error("▶▶▶ Unauthorized error: {}", authException.getMessage());
		log.error("▶▶▶ Forward to Login page======>");
		request.getRequestDispatcher(AuthConst.APP_LOGIN_URL).forward(request, response);
	}

}
