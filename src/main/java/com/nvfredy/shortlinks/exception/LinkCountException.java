package com.nvfredy.shortlinks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LinkCountException extends ResponseStatusException {
    public LinkCountException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
