package com.humana.dhp.eventproc.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;

@Component
@RestControllerEndpoint(id = "rest-end-point")
public class ClusterDefaultConfig {

    @Value("${spring.profiles.active}")
    private String env;

    @GetMapping("/custom")
    public @ResponseBody ResponseEntity customEndPoint() throws IOException {
        File resource = new ClassPathResource(MessageFormat.format("cluster_init_default/{0}.json", env)).getFile();
        String text = new String(Files.readAllBytes(resource.toPath()));
        return new ResponseEntity<>(text, HttpStatus.OK);
    }
}
