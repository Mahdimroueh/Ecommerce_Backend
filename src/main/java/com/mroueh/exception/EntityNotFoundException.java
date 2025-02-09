package com.mroueh.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entity) {
        super(entity + " not found with id: ");
    }
}