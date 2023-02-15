package com.humana.dhp.eventproc.portal.utils;

import com.humana.dhp.eventproc.portal.dto.ResponseBodyDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class ResponseHandler {

    private ResponseHandler() {}

    public static ResponseEntity<String> buildCustomReponse(int code, String message) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        ResponseBodyDTO responseBodyDTO = new ResponseBodyDTO(message, LocalDate.now().toString());
        return ResponseEntity
                .status(HttpStatus.valueOf(code))
                .headers(responseHeaders)
                .body(JsonUtil.toJsonString(responseBodyDTO));
    }
}
