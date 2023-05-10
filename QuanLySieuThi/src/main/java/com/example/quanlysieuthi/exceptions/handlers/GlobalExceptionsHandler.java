package com.example.quanlysieuthi.exceptions.handlers;

import com.example.quanlysieuthi.dto.response.ErrorResponse;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ ResourceNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handleCategoryNotFoundException(RuntimeException exception, WebRequest request) {
        ErrorResponse error = new ErrorResponse("404", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ResourceNotAcceptException.class })
    protected ResponseEntity<ErrorResponse> handleCategoryNotAcceptException(RuntimeException exception, WebRequest request) {
        ErrorResponse error = new ErrorResponse("406", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(RuntimeException exception,
                                                                           WebRequest request) {
        ErrorResponse error = new ErrorResponse("400", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

}
