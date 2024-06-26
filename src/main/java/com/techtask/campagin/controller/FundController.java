package com.techtask.campagin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.techtask.user.UserConfig.user;

@RestController
public class FundController {

    @GetMapping("/fund")
    public Double getFund(@RequestParam Double value){
        return user.getAccountAmount() - value;
    }

    @GetMapping("/account")
    public Double getFund2(){
        return user.getAccountAmount();
    }

}
