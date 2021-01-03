package kr.co.cesco.econtract.common.exception;

import kr.co.cesco.econtract.common.exception.type.BaseExceptionType;
import lombok.Getter;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = -1673087263700143836L;

	@Getter
	private final BaseExceptionType exceptionType;

	public BadRequestException(BaseExceptionType exceptionType){
		super(exceptionType.getMessage());
		this.exceptionType = exceptionType;
	}

}
