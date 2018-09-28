package com.entry.entrydsm.common.advice;

import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.common.exception.*;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.validate.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class APIControllerAdvice {

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleRequestValidationException(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        return buildErrorResponse(errors, err -> {
            FieldError fieldError = (FieldError) err;
            return new RestResponse.Error(fieldError.getField(), getErrorMessage(fieldError));
        });
    }

    private String getErrorMessage(FieldError fieldError) {
        Optional<String> code = getFirstCode(fieldError);
        if (!code.isPresent()) {
            return null;
        }

        String errorMessage = messageSourceAccessor.getMessage(code.get(), fieldError.getArguments(), fieldError.getDefaultMessage());
        log.info("error message: {}", errorMessage);
        return errorMessage;
    }

    private Optional<String> getFirstCode(FieldError fieldError) {
        String[] codes = fieldError.getCodes();
        if (codes == null || codes.length == 0) {
            return Optional.empty();
        }
        return Optional.of(codes[0]);
    }


    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleConflictException(ConflictException e) {
        return RestResponse.error(e.getField(), e.getMessage()).build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleBadRequestException(BadRequestException e) {
        return RestResponse.error(e.getMessage()).build();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResponse<?> handlerUnauthorizedException(UnauthorizedException e) {
        return RestResponse.error("로그인이 필요합니다.").build();
    }

    @ExceptionHandler(AlreadySubmittedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RestResponse<?> handleAlreadySubmittedException(AlreadySubmittedException e) {
        return RestResponse.error("이미 원서를 제출하셨습니다.").build();
    }

    @ExceptionHandler(SubmitValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<ValidationResult> handleSubmitValidationException(SubmitValidationException e) {
        return RestResponse.success(e.getValidationResult());
    }

    @ExceptionHandler(RequestValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleRequestValidationException(RequestValidationException e) {
        return buildErrorResponse(e.getValidationErrors(), ValidationUtil::toError);
    }

    private <T> RestResponse<?> buildErrorResponse(Iterable<T> errors, ErrorSupplier<T> errorSupplier) {
        RestResponse.ErrorResponseBuilder errorResponseBuilder = RestResponse.error();
        errors.forEach(error -> errorResponseBuilder.appendError(errorSupplier.apply(error)));
        return errorResponseBuilder.build();
    }
}
