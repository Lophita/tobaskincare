package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }));
        BaseResponse<Object> baseResponse = new BaseResponse<Object>("FAILED", "failed", null, errors);
        return baseResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public BaseResponse<Object> handleNoSuchElementException(NoSuchElementException exception, WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("not found", exception.toString());
        BaseResponse<Object> baseResponse = new BaseResponse<Object>
                ("FAILED", "failed", null, errors);
        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleAllCaughtException(Exception exception, WebRequest request) {
        log.error("Unknown error occured");

        BaseResponse<Object> baseResponse = new BaseResponse<Object>
                ("FAILED", "failed", null, null);

        return baseResponse;
    }
}
