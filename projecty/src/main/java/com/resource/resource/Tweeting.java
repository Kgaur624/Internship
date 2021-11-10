package com.resource.resource;

import com.config.DropWizardConfiguration;
import io.dropwizard.Configuration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Tweeting {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    public Tweeting() {
        configurationBuilder = DropWizardConfiguration.getConfigurationObject();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
    }
    public Tweeting(ConfigurationBuilder configurationBuilder, TwitterFactory twitterFactory) {
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory = twitterFactory;
    }


    public  Status sendTweets(String args) throws NullPointerException {
        DropWizardConfiguration dropWizardConfiguration = new DropWizardConfiguration();
        ConfigurationBuilder configurationBuilder = dropWizardConfiguration.getConfigurationObject();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        Status status = null;
        try {
            status = twitter.updateStatus(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;


    }
}



