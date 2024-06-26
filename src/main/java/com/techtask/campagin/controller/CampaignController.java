package com.techtask.campagin.controller;

import com.techtask.campagin.Campaign;
import com.techtask.campagin.CampaignMapper;
import com.techtask.campagin.CampaignService;
import com.techtask.campagin.controller.dto.request.EditCampaignRequest;
import com.techtask.campagin.controller.dto.request.NewCampaignRequest;
import com.techtask.campagin.controller.dto.response.NewCampaignResponse;
import com.techtask.campagin.dto.CampaignDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping
    public ResponseEntity<List<CampaignDto>> showAllCampaigns(){
        List<CampaignDto> allCampaigns = campaignService.getAllCampaigns();
        return ResponseEntity.ok(allCampaigns);
    }

    @PostMapping
    public ResponseEntity<NewCampaignResponse> addNewCampaign(@RequestBody NewCampaignRequest newCampaignRequest){
        System.out.println(newCampaignRequest);
        Campaign savedCampaign = campaignService.saveNewCampaign(newCampaignRequest);
        NewCampaignResponse response = NewCampaignResponse.builder()
                .campaignName(savedCampaign.getName())
                .message("Campaign added.")
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CampaignDto> editCampaign(
            @PathVariable Long id,
            @RequestBody EditCampaignRequest editCampaignRequest
    ){
        Campaign editedCampaign = campaignService.editCampaign(id, editCampaignRequest);
        CampaignDto campaignDto = CampaignMapper.mapFromCampaignToCampaignDto(editedCampaign);
        return ResponseEntity.ok(campaignDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable String id){
        long longId = Long.parseLong(id);
        campaignService.deleteCampaign(longId);
        return ResponseEntity.ok("Campaign deleted");
    }

}
