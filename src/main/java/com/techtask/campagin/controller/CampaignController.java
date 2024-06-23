package com.techtask.campagin.controller;

import com.techtask.campagin.controller.dto.request.NewCampaignRequest;
import com.techtask.campagin.controller.dto.response.NewCampaignResponse;
import com.techtask.campagin.Campaign;
import com.techtask.campagin.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public ResponseEntity<NewCampaignResponse> addNewCampaign(@RequestBody NewCampaignRequest newCampaignRequest){
        Campaign savedCampaign = campaignService.saveNewCampaign(newCampaignRequest);
        NewCampaignResponse response = NewCampaignResponse.builder()
                .campaignName(savedCampaign.getName())
                .message("Campaign added.")
                .build();
        return ResponseEntity.ok(response);
    }

}
