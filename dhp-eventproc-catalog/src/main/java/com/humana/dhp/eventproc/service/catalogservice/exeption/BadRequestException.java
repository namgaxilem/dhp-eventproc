package com.humana.dhp.eventproc.service.catalogservice.exeption;

public class BadRequestException extends CatalogException {

    private BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
