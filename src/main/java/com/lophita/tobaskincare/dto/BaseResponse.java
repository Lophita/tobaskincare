package com.lophita.tobaskincare.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class BaseResponse<T> {
    private String code;
    private String message;
    private T data;
    private Map<String, String> errors;

    public BaseResponse(String code, String message, T data, Map<String, String> errors) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }
}
