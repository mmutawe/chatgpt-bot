package com.mmutawe.projects.chatgpt.bot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptResponseDto {
    private List<Choice> choices;
}
