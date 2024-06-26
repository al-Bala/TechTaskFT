package com.techtask.campagin;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends CrudRepository<Campaign, Long> {
    Optional<Campaign> findByIdCampaign(Long id);
    List<Campaign> findAll();
}
