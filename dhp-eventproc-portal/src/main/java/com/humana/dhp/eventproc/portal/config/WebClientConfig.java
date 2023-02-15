package com.humana.dhp.eventproc.portal.config;

import com.humana.dhp.eventproc.portal.exception.BadRequestException;
import com.humana.dhp.eventproc.portal.exception.NotFoundException;
import com.humana.dhp.eventproc.portal.exception.UpstreamServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Configuration
public class WebClientConfig {

    ExchangeFilterFunction errorResponseFilter = ExchangeFilterFunction
            .ofResponseProcessor(WebClientConfig::exchangeFilterResponseProcessor);

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .filter(errorResponseFilter)
                .build();
    }

    @Bean
    public WebClient sseWebClient() {
        return WebClient.builder()
                .exchangeStrategies((ExchangeStrategies
                        .builder()
                        .codecs(config -> config
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build()))
                .filter(errorResponseFilter)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.TEXT_EVENT_STREAM));
                })
                .build();
    }

    private static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatus status = response.statusCode();
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new UpstreamServiceException(body)));
        }
        if (HttpStatus.BAD_REQUEST.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new BadRequestException(body)));
        }
        if (HttpStatus.NOT_FOUND.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new NotFoundException(body)));
        }
        return Mono.just(response);
    }

//    @Bean(name = "httpSessionRequestCache")
//    public HttpSessionRequestCache getHttpSessionRequestCache() {
//        HttpSessionRequestCache cache = new HttpSessionRequestCache();
//        cache.setCreateSessionAllowed(true);
//        return cache;
//    }
}
