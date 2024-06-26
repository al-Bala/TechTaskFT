package com.techtask.campagin.dto;

import lombok.Builder;

@Builder
public record KeywordDto(
        long idKeyword,
        String word
) {
}
