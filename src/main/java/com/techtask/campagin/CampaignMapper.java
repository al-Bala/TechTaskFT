package com.techtask.campagin;

import com.techtask.campagin.controller.dto.request.NewCampaignRequest;
import org.springframework.stereotype.Component;

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

}
