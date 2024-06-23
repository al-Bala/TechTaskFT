package com.techtask.campagin;

import com.techtask.keyword.Keyword;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campaign", nullable = false)
    private Long idCampaign;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "campaign_keyword",
            joinColumns = { @JoinColumn(name = "id_campaign") },
            inverseJoinColumns = { @JoinColumn(name = "id_keyword") }
    )
    private Set<Keyword> keywords = new HashSet<>();

    @Column(name = "bid_amount", nullable = false)
    private double bidAmount;

    @Column(nullable = false)
    private double fund;

    @Column(name = "status", nullable = false)
    private boolean isStatusOn;

    private String town;

    @Column(name = "radius", nullable = false)
    private int radiusKm;

    public Campaign(){

    }

    public Campaign(String name, double bidAmount, double fund, boolean isStatusOn, String town, int radiusKm) {
        this.name = name;
        this.bidAmount = bidAmount;
        this.fund = fund;
        this.isStatusOn = isStatusOn;
        this.town = town;
        this.radiusKm = radiusKm;
    }
}
