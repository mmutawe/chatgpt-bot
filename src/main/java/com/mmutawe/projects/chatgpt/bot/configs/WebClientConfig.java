package com.mmutawe.projects.chatgpt.bot.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class WebClientConfig {

    private static final int TIMEOUT_MS = 10_000;

    @Value("${openai.api.url}")
    private String url;

    @Value("${openai.api.key}")
    private String key;

    @Bean
    public WebClient setupWebClientWithTimeout() {
        var client = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_MS)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT_MS, MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT_MS, MILLISECONDS));
                });


        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(AUTHORIZATION,"Bearer " + key)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

}
