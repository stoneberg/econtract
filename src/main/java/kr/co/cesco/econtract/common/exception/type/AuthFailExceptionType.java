package kr.co.cesco.econtract.common.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthFailExceptionType implements BaseExceptionType {
    AUTHENTICATION_FAILED("AFE001", "아이디와 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED.value()),
    JWT_TOKEN_INVALID("AFE002", "토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED.value());

    private final String message;
    private final String code;
    private final int status;

    AuthFailExceptionType(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
