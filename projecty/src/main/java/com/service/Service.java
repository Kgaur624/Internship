package com.service;

import ch.qos.logback.access.filter.StatsByWeek;
import com.config.DropWizardConfiguration;
import com.resource.PostReq;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.List;

public class Service {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Twitter twitter;
    PostReq postReq = new PostReq();

    public Service(ConfigurationBuilder configurationBuilder,TwitterFactory twitterFactory,Twitter twitter){
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory= twitterFactory;
        this.twitter = twitter;
    }

    public Service(){
     configurationBuilder  = DropWizardConfiguration.getConfigurationObject();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
       twitter = twitterFactory.getInstance();

    }

    public ConfigurationBuilder configuration() {
        return configurationBuilder;
    }

    public TwitterFactory twitterFactory() {
        return twitterFactory;
    }

    public Status status() throws TwitterException {
        return twitter.updateStatus(postReq.getMessage());
    }
    public Twitter getTwitterInstance(){
        return  twitter;
    }

    public List<Status> timeline() throws TwitterException {
        return twitter.getHomeTimeline();
    }


}

// create one more constructor that takes argument line 17 to 20, go to test case