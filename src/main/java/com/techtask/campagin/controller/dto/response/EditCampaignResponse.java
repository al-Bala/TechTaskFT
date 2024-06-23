package com.techtask.campagin.controller.dto.response;

import lombok.Builder;

@Builder
public record EditCampaignResponse(
        String campaignName,
        String message
) {
}
