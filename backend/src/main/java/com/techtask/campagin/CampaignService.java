package com.techtask.campagin;

import com.techtask.campagin.controller.dto.request.EditCampaignRequest;
import com.techtask.campagin.controller.dto.request.NewCampaignRequest;
import com.techtask.campagin.dto.CampaignDto;
import com.techtask.keyword.Keyword;
import com.techtask.keyword.KeywordService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.techtask.user.UserConfig.user;

@AllArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final KeywordService keywordService;

    public List<CampaignDto> getAllCampaigns(){
        List<Campaign> allCampaigns = campaignRepository.findAll();
        return CampaignMapper.mapFromCampaignsToCampaignsDto(allCampaigns);
    }

    public Campaign saveNewCampaign(NewCampaignRequest request){
        Set<Keyword> keywords = keywordService.getKeywordsByWords(request.keywords());
        Campaign campaign = CampaignMapper.mapFromNewCampaignRequestToCampaign(request);
        campaign.setKeywords(keywords);
        keywordService.saveCampaignForKeywords(campaign, keywords);
        updateAccountAmount(-request.fund());
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
            double value = campaignToEdit.getFund() - request.fund();
            updateAccountAmount(value);
            campaignToEdit.setFund(request.fund());
        }
        if(request.isStatusOn() != null && campaignToEdit.isStatusOn() != request.isStatusOn()){
            campaignToEdit.setStatusOn(request.isStatusOn());
        }
        if(request.town() != null && !campaignToEdit.getTown().equals(request.town())){
            campaignToEdit.setTown(request.town());
        }
        if(request.radiusKm() != null && campaignToEdit.getRadiusKm() != request.radiusKm()){
            campaignToEdit.setRadiusKm(request.radiusKm());
        }
        return campaignRepository.save(campaignToEdit);
    }

    @Transactional
    public void deleteCampaign(Long id){
        Campaign campaign = campaignRepository.findByIdCampaign(id).orElseThrow();
        campaignRepository.delete(campaign);
        updateAccountAmount(campaign.getFund());
    }

    private static void updateAccountAmount(double value){
        user.setAccountAmount(user.getAccountAmount() + value);
    }
}
