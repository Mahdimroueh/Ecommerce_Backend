package com.mroueh.utils;

import com.mroueh.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    // Generic method to handle the 'created' response (HTTP 201)
    public <T> ResponseEntity<T> created(T res) {
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    // Generic method to handle the 'unauthorized' response (HTTP 401)
    public <T> ResponseEntity<T> unauthorized(T res) {
        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }

    // Generic method to handle the 'notFound' response (HTTP 404)
    public <T> ResponseEntity<T> notFound(T res) {
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    // Generic method to handle the 'badRequest' response (HTTP 400)
    public <T> ResponseEntity<T> badRequest(T res) {
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    // Generic method to handle the 'internalServerError' response (HTTP 500)
    public <T> ResponseEntity<T> internalServerError(T res) {
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Generic method to handle the 'forbidden' response (HTTP 403)
    public <T> ResponseEntity<T> forbidden(T res) {
        return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
    }

    // Generic method to handle the 'ok' response (HTTP 200)
    public <T> ResponseEntity<T> ok(T res) {
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // Generic method to handle the 'noContent' response (HTTP 204)
    public <T> ResponseEntity<T> noContent(T res) {
        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }

    // Generic method to handle the 'accepted' response (HTTP 202)
    public <T> ResponseEntity<T> accepted(T res) {
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    // Generic method to handle the 'movedPermanently' response (HTTP 301)
    public <T> ResponseEntity<T> movedPermanently(T res) {
        return new ResponseEntity<>(res, HttpStatus.MOVED_PERMANENTLY);
    }

    // Generic method to handle the 'conflict' response (HTTP 409)
    public <T> ResponseEntity<T> conflict(T res) {
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    // Generic method to handle the 'unprocessableEntity' response (HTTP 422)
    public <T> ResponseEntity<T> unprocessableEntity(T res) {
        return new ResponseEntity<>(res, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
