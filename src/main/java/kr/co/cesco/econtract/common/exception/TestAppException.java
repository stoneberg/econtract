package kr.co.cesco.econtract.common.exception;

import kr.co.cesco.econtract.common.exception.type.BaseExceptionType;
import lombok.Getter;

public class TestAppException extends RuntimeException {
	private static final long serialVersionUID = -3254654178541145856L;

	@Getter
	private final BaseExceptionType exceptionType;

	public TestAppException(BaseExceptionType exceptionType){
		super(exceptionType.getMessage());
		this.exceptionType = exceptionType;
	}

}
