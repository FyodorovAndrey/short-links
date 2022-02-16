package com.nvfredy.shortlinks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LinkNotFoundException extends ResponseStatusException {
    public LinkNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
