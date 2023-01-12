package com.example.transactionapi.Dto;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ApiResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final HttpStatus status;
    private final Integer statusCode;
    private final String message;
    private final T data;

    private ApiResponseDto(HttpStatus status, String message, T data) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseDto<T> of(HttpStatus status, String message, T data) {
        return new ApiResponseDto<>(status, message, data);
    }

    public static ApiResponseDto<EmptyResponseDto> ok() {
        return ApiResponseDto.ok(new EmptyResponseDto());
    }

    public static <T> ApiResponseDto<T> ok(T data) {
        return ApiResponseDto.of(HttpStatus.OK, "", data);
    }

    public static ApiResponseDto<EmptyResponseDto> ofError(HttpStatus status, String message) {
        return ApiResponseDto.of(status, message, new EmptyResponseDto());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
