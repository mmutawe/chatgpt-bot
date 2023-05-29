package com.mmutawe.projects.chatgpt.bot.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptRequestDto {
    private String model;
    private List<Message> messages;
}
