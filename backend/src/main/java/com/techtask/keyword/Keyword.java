package com.techtask.keyword;

import com.techtask.campagin.Campaign;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_keyword", nullable = false)
    private long idKeyword;

    @Column(nullable = false)
    private String word;

    @ManyToMany(mappedBy = "keywords")
    private Set<Campaign> campaigns = new HashSet<>();

    public Keyword(){

    }

    public Keyword(String word) {
        this.word = word;
    }
}
