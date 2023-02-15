package com.humana.dhp.eventproc.portal.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

public class UriBuilder {

    private UriBuilder() {}

    public static String build(HttpServletRequest request) {
        String restOfTheUrl = new AntPathMatcher()
                .extractPathWithinPattern(
                        request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString(),
                        request.getRequestURI()
                );

        String queryParams = request.getQueryString();

        if (queryParams != null && !queryParams.isEmpty()) {
            restOfTheUrl = restOfTheUrl.concat("?" + queryParams);
        }

        return restOfTheUrl;
    }
}
