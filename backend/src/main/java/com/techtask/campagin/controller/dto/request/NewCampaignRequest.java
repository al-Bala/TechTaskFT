package com.techtask.campagin.controller.dto.request;

import org.hibernate.mapping.Array;

import java.util.List;
import java.util.Set;

public record NewCampaignRequest(
        String name,
        Set<String> keywords,
        double bidAmount,
        double fund,
        boolean isStatusOn,
        String town,
        int radiusKm
) {
}
