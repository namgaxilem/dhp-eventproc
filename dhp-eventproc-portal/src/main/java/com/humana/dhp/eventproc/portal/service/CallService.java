package com.humana.dhp.eventproc.portal.service;

import com.humana.dhp.eventproc.portal.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class CallService {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer %s";

    private final WebClient webClient;

    @Autowired
    public CallService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String get(OAuth2AuthorizedClient client, String uri) {
        return this.webClient
                .get()
                .uri(uri)
                .header(AUTHORIZATION, String.format(BEARER, client.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String post(OAuth2AuthorizedClient client, String uri, Object body) {
        return this.webClient
                .post()
                .uri(uri)
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(JsonUtil.toJsonString(body)))
                .header(AUTHORIZATION, String.format(BEARER, client.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String put(OAuth2AuthorizedClient client, String uri, Object body) {
        return this.webClient
                .put()
                .uri(uri)
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(JsonUtil.toJsonString(body)))
                .header(AUTHORIZATION, String.format(BEARER, client.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String delete(OAuth2AuthorizedClient client, String uri) {
        return this.webClient
                .delete()
                .uri(uri)
                .header(AUTHORIZATION, String.format(BEARER, client.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public Flux<String> deploymentTimelineSSE(OAuth2AuthorizedClient client, String uri) {
        return this.webClient
                .get()
                .uri(uri)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .header(AUTHORIZATION, String.format(BEARER, client.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToFlux(String.class);
    }

}
