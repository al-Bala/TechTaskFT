package com.techtask;

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
    @Column(nullable = false)
    private long id_keyword;

    @Column(nullable = false)
    private String word;

    @ManyToMany(mappedBy = "keywords")
    private Set<Campaign> campaigns = new HashSet<>();

    public Keyword(){

    }
}
