package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tweeting{
    @Autowired
    Services services;

    public Tweeting (){}
    public Tweeting(Services services){
        this.services = services;
    }




}


