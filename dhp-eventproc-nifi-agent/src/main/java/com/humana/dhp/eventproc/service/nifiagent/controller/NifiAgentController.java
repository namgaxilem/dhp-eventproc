package com.humana.dhp.eventproc.service.nifiagent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NifiAgentController {

//    @PostMapping("/v1/nifi-agent")
//    @PreAuthorize("hasAuthority('SCOPE_Obo.NifiAgent')")
//    public ResponseEntity<String> getNifiAgent(@RequestBody Object body) {
//        return new ResponseEntity<>("/v1/nifi-agent -> this is data from nifi agent" + body, HttpStatus.OK);
//    }

    @GetMapping("/v1/nifi-agent/**")
    public String getNifiAgent(HttpServletRequest request) {
        String finalPath = new AntPathMatcher()
                .extractPathWithinPattern(
                        request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString(),
                        request.getRequestURI()
                );
        System.out.println(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString() + "-----" +
                request.getRequestURI());
        String queryParams = request.getQueryString();
        System.out.println(request.getMethod());
        return "Test: " + finalPath + "?" + queryParams;
    }
}
