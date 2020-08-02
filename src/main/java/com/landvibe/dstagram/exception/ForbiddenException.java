package com.landvibe.dstagram.exception;

import java.nio.file.AccessDeniedException;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String exception) {
        super(exception);
    }
}
