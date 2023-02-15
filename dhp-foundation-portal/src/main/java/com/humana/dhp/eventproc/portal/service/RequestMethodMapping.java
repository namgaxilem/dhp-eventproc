package com.humana.dhp.eventproc.portal.service;

import com.humana.dhp.eventproc.portal.exception.UpstreamServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class RequestMethodMapping {

    private final CallService callService;

    @Autowired
    public RequestMethodMapping(CallService callService) {
        this.callService = callService;
    }

    public String checkMethodAndCallService(HttpServletRequest request, OAuth2AuthorizedClient client, String uri, Object body) {
        try {
            switch (request.getMethod()) {
                case "GET":
                    return callService.get(client, uri);
                case "POST":
                    return callService.post(client, uri, body);
                case "PUT":
                    return callService.put(client, uri, body);
                case "DELETE":
                    return callService.delete(client, uri);
                default:
                    return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UpstreamServiceException(e.getMessage());
        }
    }

    public Flux<String> deploymentTimelineSSE(OAuth2AuthorizedClient client, String uri) {
        return this.callService.deploymentTimelineSSE(client, uri);
    }
}
