package kr.co.cesco.econtract.common.exception.handler;

import kr.co.cesco.econtract.common.exception.*;
import kr.co.cesco.econtract.common.exception.reponse.ResponseError;
import kr.co.cesco.econtract.common.exception.type.BaseExceptionType;
import kr.co.cesco.econtract.common.exception.type.SystemExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomGlobalRestExceptionHandler {

    ///////////////// I.CUSTOM EXCEPTION /////////////////////

    // Test App Error
    @ExceptionHandler(TestAppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ResponseError handleTestAppException(TestAppException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        return buildError(Error.create(ex.getExceptionType()), request);
    }

    // Not Found
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ResponseError handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        return buildError(Error.create(ex.getExceptionType()), request);
    }

    // Bad Request
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handlerBadRequestException(BadRequestException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        return buildError(Error.create(ex.getExceptionType()), request);
    }

    // FileControlException
    @ExceptionHandler(FileControlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handlerStorageException(FileControlException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        return buildError(Error.create(ex.getExceptionType()), request);
    }

    // Authentication failed
    @ExceptionHandler(AuthFailException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    protected ResponseError handlerAuthenticationFailedException(AuthFailException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        return buildError(Error.create(ex.getExceptionType()), request);
    }

    ///////////////// II.SYSTEM EXCEPTION /////////////////////

    // Method not support
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        final List<ResponseError.FieldError> fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildFieldErrors(request, fieldErrors);
    }

    // Invalid argument type
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        return buildError(SystemExceptionType.INPUT_VALUE_INVALID, ex.getMessage(), request);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleBindException(BindException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        final List<ResponseError.FieldError> fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildFieldErrors(request, fieldErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ResponseError handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        final SystemExceptionType errorCode = SystemExceptionType.INPUT_VALUE_INVALID;
        final String message = getResultMessage(ex.getConstraintViolations().iterator());
        return buildError(errorCode, message, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        return buildError(SystemExceptionType.INPUT_VALUE_INVALID, request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        return buildError(SystemExceptionType.METHOD_NOT_ALLOWED, request);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleHttpMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        return buildError(SystemExceptionType.FILE_UPLOAD_FAILED, request);
    }

    // JPA, QueryDSL
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    protected ResponseError handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        return buildError(SystemExceptionType.DATA_ACCESS_FAILED, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    protected ResponseError handleAnyException(Exception ex, WebRequest request) {
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getMessage(ex));
        log.error("▒▒▒▒▒▒▒▒ EXCEPTION ▒▒▒▒▒▒▒▒ {}", ExceptionUtils.getStackTrace(ex));
        return buildError(SystemExceptionType.SERVER_EXCEPTION, request);
    }

    private ResponseError buildError(Error error, WebRequest request) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .success(Boolean.FALSE.toString())
                .code(error.getCode())
                .status(error.getStatus())
                .path(request.getDescription(false))
                .message(error.getMessage())
                .build();
    }

    private ResponseError buildError(SystemExceptionType errorCode, WebRequest request) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .success(Boolean.FALSE.toString())
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .path(request.getDescription(false))
                .message(errorCode.getMessage())
                .build();
    }

    private ResponseError buildError(SystemExceptionType errorCode, String message, WebRequest request) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .success(Boolean.FALSE.toString())
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .path(request.getDescription(false))
                .message(message)
                .build();
    }

    private ResponseError buildFieldErrors(WebRequest request, List<ResponseError.FieldError> errors) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .success(Boolean.FALSE.toString())
                .code(SystemExceptionType.INPUT_VALUE_INVALID.getCode())
                .status(SystemExceptionType.INPUT_VALUE_INVALID.getStatus())
                .path(request.getDescription(false))
                .message(errors.get(0).getReason())
                .errors(errors)
                .build();
    }

    /**
     * find MethodArgumentNotValidException message
     *
     * @param bindingResult
     * @return
     */
    private List<ResponseError.FieldError> getFieldErrors(BindingResult bindingResult) {
        final List<FieldError> errors = bindingResult.getFieldErrors();
        if (!errors.isEmpty()) {
            return errors.parallelStream()
                    .map(error -> ResponseError.FieldError.builder()
                            .reason(error.getDefaultMessage())
                            .field(error.getField())
                            .value(error.getRejectedValue() != null ? error.getRejectedValue().toString() : StringUtils.EMPTY)
                            .build())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * find ConstraintViolationException message
     *
     * @param violationIterator
     * @return
     */
    protected String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder
                    .append("['")
                    .append(getPropertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
                    .append("' is '")
                    .append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
                    .append("'. ")
                    .append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
                    .append("]");

            if (violationIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        return resultMessageBuilder.toString();
    }

    protected String getPropertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Error {
        private String message;
        private String code;
        private int status;

        static Error create(BaseExceptionType exception) {
            return new Error(exception.getMessage(), exception.getCode(), exception.getStatus());
        }
    }

}
