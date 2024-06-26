package com.techtask.keyword;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {
    Optional<Keyword> findKeywordByWord(String word);
    List<Keyword> findByWordContainingIgnoreCase(String word);
}
