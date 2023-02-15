package com.humana.dhp.eventproc.portal.controller;

import com.humana.dhp.eventproc.portal.service.RequestMethodMapping;
import com.humana.dhp.eventproc.portal.utils.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

@RestController
@Slf4j
public class DeploymentController {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer %s";
    private final RequestMethodMapping requestMethodMapping;
    private final WebClient sseWebClient;

    @Autowired
    public DeploymentController(RequestMethodMapping requestMethodMapping,
                                WebClient sseWebClient) {
        this.requestMethodMapping = requestMethodMapping;
        this.sseWebClient = sseWebClient;
    }

    @RequestMapping("/api/user-request/**")
    public String userRequest(
            @RegisteredOAuth2AuthorizedClient("dhp-eventproc-deployment") OAuth2AuthorizedClient client,
            HttpServletRequest request,
            @RequestBody(required = false) Object body
    ) {

        String uri = "http://localhost:8181/v1/user-request/" + UriBuilder.build(request);
        return requestMethodMapping.checkMethodAndCallService(request, client, uri, body);
    }

    @RequestMapping("/api/deployment/**")
    public String deployment(
            @RegisteredOAuth2AuthorizedClient("dhp-eventproc-deployment") OAuth2AuthorizedClient client,
            HttpServletRequest request,
            @RequestBody(required = false) Object body
    ) {

        String uri = "http://localhost:8181/v1/deployment/" + UriBuilder.build(request);
        return requestMethodMapping.checkMethodAndCallService(request, client, uri, body);
    }

    @GetMapping(path = "/api/deployment/{deploymentId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents(
            @PathVariable String deploymentId,
            @RegisteredOAuth2AuthorizedClient("dhp-eventproc-deployment") OAuth2AuthorizedClient client
    ) {
        String uri = "http://localhost:8181/v1/deployment/" + UUID.randomUUID();
        return this.requestMethodMapping.deploymentTimelineSSE(client, uri);
    }

    @RequestMapping("/api/approval/**")
    public String approval(
            @RegisteredOAuth2AuthorizedClient("dhp-eventproc-deployment") OAuth2AuthorizedClient client,
            HttpServletRequest request,
            @RequestBody(required = false) Object body
    ) {

        String uri = "http://localhost:8181/v1/approval/" + UriBuilder.build(request);
        return requestMethodMapping.checkMethodAndCallService(request, client, uri, body);
    }

    @GetMapping("/api/deployment/deploymentTimelineSSE")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1)).log()
                .map(sequence -> ServerSentEvent.<String> builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }
}
