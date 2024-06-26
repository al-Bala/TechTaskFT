package com.techtask.user;

import com.techtask.campagin.Campaign;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class User {

    private String login;
    private String password;
    private double accountAmount;
    private final Map<Long, Campaign> userCampaigns = new HashMap<>();

    public User(String login, String password, double accountAmount) {
        this.login = login;
        this.password = password;
        this.accountAmount = accountAmount;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
