package com.humana.dhp.eventproc.service.deployment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import javax.ws.rs.InternalServerErrorException;
import java.time.Duration;
import java.util.Collections;

@Configuration
public class WebClientConig {
  private final ExchangeFilterFunction errorResponseFilter = ExchangeFilterFunction
      .ofResponseProcessor(WebClientConig::exchangeFilterResponseProcessor);

  @Bean
  public WebClient webClient() {
    ConnectionProvider connectionProvider = ConnectionProvider
        .builder("custom")
        .maxConnections(50)
        .maxIdleTime(Duration.ofSeconds(60))
        .build();
    HttpClient httpClient = HttpClient.create(connectionProvider);

    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .exchangeStrategies((ExchangeStrategies
            .builder()
            .codecs(config -> config
                .defaultCodecs()
                .maxInMemorySize(16 * 1024 * 1024))
            .build()))
        .filter(errorResponseFilter)
        .defaultHeaders(httpHeaders -> {
          httpHeaders.setContentType(MediaType.APPLICATION_JSON);
          httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        })
        .build();
  }

  private static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
    HttpStatus status = response.statusCode();
    if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
      return response.bodyToMono(String.class).flatMap(body -> Mono.error(new InternalServerErrorException(body)));
    }
    return Mono.just(response);
  }
}
