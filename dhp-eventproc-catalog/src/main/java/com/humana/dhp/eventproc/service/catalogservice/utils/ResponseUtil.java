package com.humana.dhp.eventproc.service.catalogservice.utils;

import com.humana.dhp.eventproc.service.catalogservice.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<Object> success(Object obj){
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    public static ResponseEntity<Object> systemError(){
        return new ResponseEntity<>("",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> notFound(){
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<Object> badRequest(Object error){
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> noAuthor(Object error){
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);

    }

}
