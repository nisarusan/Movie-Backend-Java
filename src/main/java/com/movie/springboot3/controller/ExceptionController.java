package com.movie.springboot3.controller;

import com.movie.springboot3.exception.DuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
@ControllerAdvice
public class ExceptionController {
@ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Object> duplicateException (DuplicateException duplicateException) {
    return new ResponseEntity<>(duplicateException.getMessage(), HttpStatus.CONFLICT);
}


}
