package com.techtask.campagin.controller.dto.response;

import lombok.Builder;

@Builder
public record NewCampaignResponse(
        String campaignName,
        String message
) {
}
