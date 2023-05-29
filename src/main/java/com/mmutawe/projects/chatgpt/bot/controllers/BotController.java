package com.mmutawe.projects.chatgpt.bot.controllers;

import com.mmutawe.projects.chatgpt.bot.dtos.ChatGptRequestDto;
import com.mmutawe.projects.chatgpt.bot.dtos.ChatGptResponseDto;
import com.mmutawe.projects.chatgpt.bot.dtos.Message;
import com.mmutawe.projects.chatgpt.bot.dtos.PromptRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/chatgpt/bot")
@RequiredArgsConstructor
public class BotController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    private final WebClient webClient;

    @GetMapping
    public ResponseEntity<ChatGptResponseDto> chat(@RequestBody PromptRequestDto promptDto) {

        ChatGptRequestDto requestDto = ChatGptRequestDto.builder()
                .model(model)
                .messages(List.of(Message.builder()
                        .role("user")
                        .content(promptDto.getPrompt())
                        .build()))
                .build();

        ChatGptResponseDto response = webClient.post()
                .body(Mono.just(requestDto), ChatGptRequestDto.class)
                .retrieve()
                .bodyToMono(ChatGptResponseDto.class)
                .block();

        log.info("ChatGPT response: {}", Objects.requireNonNull(response).toString());

        return ResponseEntity
                .ok(response);
    }
}
