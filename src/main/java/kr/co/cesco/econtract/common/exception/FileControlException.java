package kr.co.cesco.econtract.common.exception;

import kr.co.cesco.econtract.common.exception.type.BaseExceptionType;
import lombok.Getter;

public class FileControlException extends RuntimeException {
    private static final long serialVersionUID = -8541287263700146358L;

    @Getter
    private final BaseExceptionType exceptionType;

    public FileControlException(BaseExceptionType exceptionType){
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
