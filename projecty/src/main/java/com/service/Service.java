package com.service;

import com.config.DropWizardConfiguration;
import com.resource.PostReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class Service {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Twitter twitter;
    PostReq postReq = new PostReq();
    Logger logger = LoggerFactory.getLogger(Tweeting.class);

    //use for test case
    public Service(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
        this.twitter = this.twitterFactory.getInstance();
    }

    //use for controller class
    public Service() {
        configurationBuilder = DropWizardConfiguration.getConfigurationObject();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }


    public Status sendTweets(String args) throws NullPointerException, TwitterException {
        Status status = null;
        try {
            if (args.length() != 0)
                status = twitter.updateStatus(postReq.getMessage());
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


    public List<String> latestTweet() {
        List<String> list = new ArrayList<>();
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                list.add(status.getText());
            }
            if (list.isEmpty()) {
                logger.info("You Have No Tweets On your Timeline");
                list.add("No Tweet Found On TimeLine");
            }
            // return list;
        } catch (TwitterException e) {
            logger.error("Error Occur", e);
            //  throw new RuntimeException("Run time error");
        }
        return list;
    }
}
