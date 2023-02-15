package com.humana.dhp.eventproc.service.deployment.controller;

import com.nimbusds.jose.util.JSONStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.ws.rs.Path;
import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@Slf4j
public class FlowDeploymentController {

    private final WebClient webClient;

    @Autowired
    public FlowDeploymentController(WebClient webClient) {
        this.webClient = webClient;
    }

    @PreAuthorize("hasAuthority('SCOPE_Obo.Deployment.NifiScope')")
    @PostMapping("/v1/deployment")
    public ResponseEntity<String> createDeployment(
            @RegisteredOAuth2AuthorizedClient("dhp-eventproc-nifi-agent") OAuth2AuthorizedClient nifiService,
            @RequestBody Object body
    ) {
        return new ResponseEntity<>(callNifiAgent(nifiService, body) + body.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/v1/deployment/{deploymentId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@Async("flowDeploymentAsyncExecutor")
    public Flux<String> streamEvents(@PathVariable UUID deploymentId) {
        return Flux.interval(Duration.ofSeconds(1))
            .map(sequence -> "Flux - " + deploymentId + LocalTime.now().toString()).log();
    }

    private String callNifiAgent(OAuth2AuthorizedClient nifiService, Object reqBody) {
        if (null != nifiService) {
            System.out.println(nifiService.getAccessToken().getTokenValue());

            String body = this.webClient
                    .post()
                    .uri("http://localhost:8383/v1/nifi-agent")
                    .accept(MediaType.ALL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", String.format("Bearer %s", nifiService.getAccessToken().getTokenValue()))
                    .body(BodyInserters.fromValue(JSONStringUtils.toJSONString(reqBody.toString())))
                    .attributes(oauth2AuthorizedClient(nifiService))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return "/v1/deployment -> " + (null != body ? body : "null");
        } else {
            return "/v1/deployment -> failed.";
        }
    }
}
