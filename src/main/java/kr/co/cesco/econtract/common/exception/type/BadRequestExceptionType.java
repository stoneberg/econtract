package kr.co.cesco.econtract.common.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BadRequestExceptionType implements BaseExceptionType {
    USER_EXISTS("BRT001", "이미 등록된 사용자 입니다.", HttpStatus.BAD_REQUEST.value()),
    EMAIL_DUPLICATION("BRT002", "사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST.value()),
    INPUT_VALUE_INVALID("BRT003", "입력 값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST.value()),
    BAD_REQUEST_FOUND("BRT004","요청 사항이 누락되었거나 잘못되었습니다.", HttpStatus.BAD_REQUEST.value());

    private final String message;
    private final String code;
    private final int status;

    BadRequestExceptionType(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
