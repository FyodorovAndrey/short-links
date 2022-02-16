package com.nvfredy.shortlinks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LinkExpiredException extends ResponseStatusException {
    public LinkExpiredException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
