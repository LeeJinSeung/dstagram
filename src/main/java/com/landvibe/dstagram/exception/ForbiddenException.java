package com.landvibe.dstagram.exception;

import java.nio.file.AccessDeniedException;

public class ForbiddenException extends AccessDeniedException {
    public ForbiddenException(String exception) {
        super(exception);
    }
}
