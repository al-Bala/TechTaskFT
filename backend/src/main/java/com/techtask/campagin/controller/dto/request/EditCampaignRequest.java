package com.techtask.campagin.controller.dto.request;

public record EditCampaignRequest(
        String name,
        Double bidAmount,
        Double fund,
        Boolean isStatusOn,
        String town,
        Integer radiusKm
) {
}
