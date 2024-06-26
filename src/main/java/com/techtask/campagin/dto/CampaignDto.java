package com.techtask.campagin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
public record CampaignDto(
        Long id,
        String name,
        Set<String> keywords,
        double bidAmount,
        double fund,
        boolean isStatusOn,
        String town,
        int radiusKm
) {
}
