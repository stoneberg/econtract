package kr.co.cesco.econtract.common.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FileControlExceptionType implements BaseExceptionType {
    FILE_PROCESSING_FAILED("FCE001", "파일 처리 중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST.value());

    private final String message;
    private final String code;
    private final int status;

    FileControlExceptionType(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
