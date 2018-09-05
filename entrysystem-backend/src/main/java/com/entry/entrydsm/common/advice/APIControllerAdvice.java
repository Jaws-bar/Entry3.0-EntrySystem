package com.entry.entrydsm.common.advice;

import com.entry.entrydsm.common.exception.ConflictException;
import com.entry.entrydsm.common.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class APIControllerAdvice {

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleValidationException(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        RestResponse.ErrorResponseBuilder errorResponseBuilder = RestResponse.error();
        for (ObjectError objectError : errors) {
            log.debug("object error : {}", objectError);
            FieldError fieldError = (FieldError) objectError;
            errorResponseBuilder.appendError(fieldError.getField(), getErrorMessage(fieldError));
        }
        return errorResponseBuilder.build();
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
}
