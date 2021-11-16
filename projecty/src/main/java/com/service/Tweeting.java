package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Tweeting {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Logger logger = LoggerFactory.getLogger(Tweeting.class);
    Service service;
    public Tweeting() {
        service = new Service();
        configurationBuilder = service.configuration();
        twitterFactory = service.twitterFactory();
    }

    public Tweeting(ConfigurationBuilder configurationBuilder, TwitterFactory twitterFactory) {
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory = twitterFactory;
    }

    //SUT
    public Status sendTweets(String args) throws NullPointerException, TwitterException {
        Status status = null;
        try {
            if (args.length() != 0)
                //external Dependency
                status = service.status();
            else
                status = null;
        } catch (Exception e) {
            if (args.length() > 280) {
                logger.error("Tweet Length is too long");
                throw new TwitterException("Tweet needs to be a shorter");
            }
        }
        return status;

    }
}



