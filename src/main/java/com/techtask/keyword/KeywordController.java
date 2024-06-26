package com.techtask.keyword;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
public class KeywordController {

    private final KeywordRepository keywordRepository;

    @GetMapping("/keywords")
    public List<String> getKeywords(@RequestParam String query) {
        if(query.isEmpty()){
            return Collections.emptyList();
        }
        List<Keyword> keywords = keywordRepository.findByWordContainingIgnoreCase(query);
        return keywords.stream()
                .map(Keyword::getWord)
                .toList();
    }
}
