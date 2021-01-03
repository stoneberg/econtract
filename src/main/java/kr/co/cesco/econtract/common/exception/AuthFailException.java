package kr.co.cesco.econtract.common.exception;

import kr.co.cesco.econtract.common.exception.type.BaseExceptionType;
import lombok.Getter;

public class AuthFailException extends RuntimeException {
    private static final long serialVersionUID = 4698980797856410517L;

    @Getter
    private final BaseExceptionType exceptionType;

    public AuthFailException(BaseExceptionType exceptionType){
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
