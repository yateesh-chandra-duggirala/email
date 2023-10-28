package com.project.email.controlleradvice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.email.exception.UnexpectedException;
import com.project.email.response.Response;

@SuppressWarnings("rawtypes")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<Response> UnexpectedExceptionHandler(UnexpectedException exception){
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = exception.getMessage();
        Response response = new Response(code, message);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> methodArgumentNotValidHandler(MethodArgumentNotValidException exception){
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
}
