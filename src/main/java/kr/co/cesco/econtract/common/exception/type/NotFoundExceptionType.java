package kr.co.cesco.econtract.common.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum NotFoundExceptionType implements BaseExceptionType {
    USER_NOT_FOUND("NFE_001", "등록된 사용자가 아닙니다.", HttpStatus.NOT_FOUND.value()),
    RESOURCE_NOT_FOUND("NFE_002","요청한 자원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
    CODE_NOT_FOUND("NFE_003","요청한 코드를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value());

    private final String message;
    private final String code;
    private final int status;

    NotFoundExceptionType(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
