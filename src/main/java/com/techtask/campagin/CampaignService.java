package com.techtask.campagin;

import com.techtask.campagin.controller.dto.request.EditCampaignRequest;
import com.techtask.campagin.controller.dto.request.NewCampaignRequest;
import com.techtask.keyword.Keyword;
import com.techtask.keyword.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final KeywordService keywordService;

    public Campaign saveNewCampaign(NewCampaignRequest request){
        Set<Keyword> keywords = keywordService.getKeywordsByWords(request.keywords());
        Campaign campaign = CampaignMapper.mapFromNewCampaignRequestToCampaign(request);
        campaign.setKeywords(keywords);
        keywordService.saveCampaignForKeywords(campaign, keywords);
        return campaignRepository.save(campaign);
    }

    public Campaign editCampaign(Long id, EditCampaignRequest request){
        Campaign campaignToEdit = campaignRepository.findByIdCampaign(id).orElseThrow();

        if(request.name() != null && !campaignToEdit.getName().equals(request.name())){
            campaignToEdit.setName(request.name());
        }
        if(request.bidAmount() != null && campaignToEdit.getBidAmount() != request.bidAmount()){
            campaignToEdit.setBidAmount(request.bidAmount());
        }
        if(request.fund() != null && campaignToEdit.getFund() != request.fund()){
            campaignToEdit.setFund(request.fund());
        }
        if(request.isStatusOn() != null && campaignToEdit.isStatusOn() != request.isStatusOn()){
            campaignToEdit.setStatusOn(request.isStatusOn());
        }
        if(request.radiusKm() != null && campaignToEdit.getRadiusKm() != request.radiusKm()){
            campaignToEdit.setRadiusKm(request.radiusKm());
        }
        return campaignRepository.save(campaignToEdit);
    }
}
