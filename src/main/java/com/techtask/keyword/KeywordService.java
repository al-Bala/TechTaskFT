package com.techtask.keyword;

import com.techtask.campagin.Campaign;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public Set<Keyword> getKeywordsByWords(Set<String> keywordsText) {
        Set<Keyword> keywords = new HashSet<>();
        for (String text : keywordsText) {
            Keyword keyword = keywordRepository.findKeywordByWord(text).orElseThrow();
            keywords.add(keyword);
        }
        return keywords;
    }

    public void saveCampaignForKeywords(Campaign campaign, Set<Keyword> keywords){
        for(Keyword keyword: keywords){
            keyword.getCampaigns().add(campaign);
        }
        keywordRepository.saveAll(keywords);
    }
}


