package kr.co.cesco.econtract.common.exception;

import kr.co.cesco.econtract.common.exception.type.BaseExceptionType;
import lombok.Getter;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5236654178541145856L;

	@Getter
	private final BaseExceptionType exceptionType;

	public NotFoundException(BaseExceptionType exceptionType){
		super(exceptionType.getMessage());
		this.exceptionType = exceptionType;
	}

}
