package com.example.Exception;

import com.example.transactionapi.Dto.ApiResponseDto;
import com.example.transactionapi.Dto.EmptyResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponseDto<EmptyResponseDto> handleException(ResourceNotFoundException ex) {

        return ApiResponseDto.ofError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ApiResponseDto<EmptyResponseDto> handleException(Exception ex) {

        return ApiResponseDto.ofError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            DataAccessException.class,
            PersistenceException.class,
            IOException.class
    })
    public ApiResponseDto<EmptyResponseDto> handleException(RuntimeException ex) {

        return ApiResponseDto.ofError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
