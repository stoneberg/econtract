package kr.co.cesco.econtract.common.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SystemExceptionType {

	INPUT_VALUE_INVALID("SE001", "입력 값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST.value()),
	METHOD_NOT_ALLOWED("SE002","메서드 요청형식(GET, POST, PUT, DELETE)이 정확하지 않습니다.", HttpStatus.METHOD_NOT_ALLOWED.value()),
	RESOURCE_NOT_FOUND("SE003","요청한 자원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
	BAD_REQUEST_FOUND("SE004","요청 사항이 누락되었거나 잘못되었습니다.", HttpStatus.BAD_REQUEST.value()),
	FILE_UPLOAD_FAILED("SE005", "개별 파일 용량이 30MB를 초과한 경우 업로드할 수 없습니다.", HttpStatus.BAD_REQUEST.value()),
	DATA_ACCESS_FAILED("SE006", "데이터 조회 시 오류가 발생했습니다.", HttpStatus.BAD_REQUEST.value()),
	// MAIL_SEND_FAILED("FAILURE_016", "메일 전송 시 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	MAIL_SEND_FAILED("SE007", "업무 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	SERVER_EXCEPTION("SERVER_FAILURE","서버 오류가 발생했습니다. 관리자에게 문의하세요.", HttpStatus.INTERNAL_SERVER_ERROR.value());
	
	private final String code;
	private final String message;
	private final int status;

	SystemExceptionType(String code, String message, int status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}

}
