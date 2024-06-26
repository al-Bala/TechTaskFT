package com.techtask.campagin;

import com.techtask.campagin.controller.dto.request.NewCampaignRequest;
import com.techtask.campagin.dto.CampaignDto;
import com.techtask.keyword.Keyword;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CampaignMapper {

    public static Campaign mapFromNewCampaignRequestToCampaign(NewCampaignRequest newCampaignRequest){
        return new Campaign(
                newCampaignRequest.name(),
                newCampaignRequest.bidAmount(),
                newCampaignRequest.fund(),
                newCampaignRequest.isStatusOn(),
                newCampaignRequest.town(),
                newCampaignRequest.radiusKm()
        );
    }

    public static List<CampaignDto> mapFromCampaignsToCampaignsDto(List<Campaign> campaigns){
        return campaigns.stream()
                .map(CampaignMapper::mapFromCampaignToCampaignDto)
                .toList();
    }

    public static CampaignDto mapFromCampaignToCampaignDto(Campaign campaign){
        return CampaignDto.builder()
                .id(campaign.getIdCampaign())
                .name(campaign.getName())
                .keywords(mapFromKeywordsToStrings(campaign.getKeywords()))
                .bidAmount(campaign.getBidAmount())
                .fund(campaign.getFund())
                .isStatusOn(campaign.isStatusOn())
                .town(campaign.getTown())
                .radiusKm(campaign.getRadiusKm())
                .build();
    }

    public static Set<String> mapFromKeywordsToStrings(Set<Keyword> keywords){
        return keywords.stream()
                .map(Keyword::getWord)
                .collect(Collectors.toSet()
        );
    }
}
