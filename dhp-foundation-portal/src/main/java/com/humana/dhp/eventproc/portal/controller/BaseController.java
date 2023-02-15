package com.humana.dhp.eventproc.portal.controller;

import com.humana.dhp.eventproc.portal.exception.BadRequestException;
import com.humana.dhp.eventproc.portal.exception.UpstreamServiceException;
import com.humana.dhp.eventproc.portal.exception.NotFoundException;
import com.humana.dhp.eventproc.portal.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BaseController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestExceptionHandler(BadRequestException e) {
        log.error(e.getMessage());
        return ResponseHandler.buildCustomReponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        log.error(e.getMessage());
        return ResponseHandler.buildCustomReponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(UpstreamServiceException.class)
    public ResponseEntity<String> upstreamServiceExceptionHandler(UpstreamServiceException e) {
        log.error(e.getMessage());
        return ResponseHandler.buildCustomReponse(HttpStatus.GATEWAY_TIMEOUT.value(), e.getMessage());
    }



}
