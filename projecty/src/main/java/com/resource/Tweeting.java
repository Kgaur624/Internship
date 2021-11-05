package com.resource;

import com.config.DropWizardConfiguration;
import io.dropwizard.Configuration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

class Tweeting {
    public static Status sendTweets(String args)  {

        ConfigurationBuilder configurationBuilder = DropWizardConfiguration.getConfigurationObject();
        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = tf.getInstance();
        Status status = null;
        try {
            status = twitter.updateStatus(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;


    }
}



