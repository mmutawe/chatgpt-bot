package com.mmutawe.projects.chatgpt.bot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptRequestDto {

    @JsonProperty("prompt")
    private String prompt;
}
