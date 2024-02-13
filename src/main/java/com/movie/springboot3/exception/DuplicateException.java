package com.movie.springboot3.exception;

public class DuplicateException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    public DuplicateException() {
        super();
    }

    //Bericht inkomend voor duplicateException
    public DuplicateException(String message) {
        super(message);
    }

}
