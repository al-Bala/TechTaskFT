package com.techtask.campagin;

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
}
