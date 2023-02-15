package com.humana.dhp.eventproc.portal.controller;

import com.humana.dhp.eventproc.portal.service.RequestMethodMapping;
import com.humana.dhp.eventproc.portal.utils.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class CatalogController extends BaseController {

    private final RequestMethodMapping requestMethodMapping;

    @Autowired
    public CatalogController(RequestMethodMapping requestMethodMapping) {
        this.requestMethodMapping = requestMethodMapping;
    }

    @RequestMapping("/api/flows/**")
    public String getFlows(
            @RegisteredOAuth2AuthorizedClient("dhp-eventproc-catalog") OAuth2AuthorizedClient client,
            HttpServletRequest request,
            @RequestBody(required = false) Object body
    ) {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        String uri = "http://localhost:8181/v1/flows/" + UriBuilder.build(request);
        return requestMethodMapping.checkMethodAndCallService(request, client, uri, body);
    }
}
