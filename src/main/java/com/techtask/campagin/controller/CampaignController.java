package com.techtask.campagin.controller;

import com.techtask.campagin.controller.dto.request.EditCampaignRequest;
import com.techtask.campagin.controller.dto.request.NewCampaignRequest;
import com.techtask.campagin.controller.dto.response.EditCampaignResponse;
import com.techtask.campagin.controller.dto.response.NewCampaignResponse;
import com.techtask.campagin.Campaign;
import com.techtask.campagin.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}")
    public ResponseEntity<EditCampaignResponse> editCampaign(
            @PathVariable Long id,
            @RequestBody EditCampaignRequest editCampaignRequest
    ){
        Campaign updatedCampaign = campaignService.editCampaign(id, editCampaignRequest);
        EditCampaignResponse response = EditCampaignResponse.builder()
                .campaignName(updatedCampaign.getName())
                .message("Campaign edited.")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long id){
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok("Campaign deleted");
    }

}
