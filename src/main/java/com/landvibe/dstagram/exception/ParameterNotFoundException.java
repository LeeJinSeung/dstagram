package com.landvibe.dstagram.exception;

public class ParameterNotFoundException extends RuntimeException {
    public ParameterNotFoundException(String exception) {
        super(exception);
    }
}
