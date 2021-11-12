package com.service;

import com.config.DropWizardConfiguration;

import com.resource.PostReq;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Service {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Twitter twitter;
    PostReq postReq;


    public Service(){
      configurationBuilder  = DropWizardConfiguration.getConfigurationObject();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
        postReq = new PostReq();
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
    public ResponseList<Status> timline() throws TwitterException {
        return twitter.getHomeTimeline();
    }

}
