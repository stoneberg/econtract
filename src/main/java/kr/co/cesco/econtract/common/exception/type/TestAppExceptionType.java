package kr.co.cesco.econtract.common.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TestAppExceptionType implements BaseExceptionType {
    CAUSE_TEST_ERROR("TAE001","테스트 오류를 발생시켰습니다.", HttpStatus.BAD_REQUEST.value()),
    CAUSE_TEST2_ERROR("TAE002","테스트 오류2를 발생시켰습니다.", HttpStatus.BAD_REQUEST.value());

    private final String message;
    private final String code;
    private final int status;

    TestAppExceptionType(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
