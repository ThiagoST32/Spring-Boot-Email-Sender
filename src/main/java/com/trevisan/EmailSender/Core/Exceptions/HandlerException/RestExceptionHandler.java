package com.trevisan.EmailSender.Core.Exceptions.HandlerException;

import com.trevisan.EmailSender.Core.Exceptions.ApiError;
import com.trevisan.EmailSender.Core.Exceptions.Exceptions.BlankArgumentException;
import com.trevisan.EmailSender.Core.Exceptions.Exceptions.InvalidEmailException;
import com.trevisan.EmailSender.Core.Exceptions.Exceptions.NullValueOnEmailOrSubject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> genericException(Exception ex) {
        return new ResponseEntity<>(ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(ex.getMessage()))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullValueOnEmailOrSubject.class)
    public ResponseEntity<ApiError> nullPointerException(NullValueOnEmailOrSubject ex) {
        return new ResponseEntity<ApiError>(ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlankArgumentException.class)
    public ResponseEntity<ApiError> illegalArgumentException(BlankArgumentException ex) {
        return new ResponseEntity<ApiError>(ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ApiError> invalidEmailException(InvalidEmailException ex) {
        return new ResponseEntity<ApiError>(ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiError> fileNotFindException(IOException ex) {
        return new ResponseEntity<ApiError>(ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of("File not found!"))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
