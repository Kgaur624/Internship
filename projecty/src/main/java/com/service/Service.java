package com.service;

import Model.TwitterData;
import com.config.DropWizardConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Service {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Twitter twitter;
    Logger logger = LoggerFactory.getLogger(Tweeting.class);
    String twitterHandle;
    String name;
    String message;
    String profileImageUrl;
    Date createdAt = null;
   TwitterData twitterData;

    //this constructor is used for test case
    public Service(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
        this.twitter = this.twitterFactory.getInstance();
    }

    public Service(TwitterFactory twitterFactory,TwitterData twitterData) {
        this.twitterFactory = twitterFactory;
        this.twitterData=twitterData;
        this.twitter = this.twitterFactory.getInstance();
    }


    //this constructor is used for controller class
    public Service() {
        configurationBuilder = DropWizardConfiguration.getConfigurationObject();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }


    public Status sendTweets(String args) throws NullPointerException, TwitterException {
        Status status = null;
        try {
            if (args.length() != 0)
                status = twitter.updateStatus(args);
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


    public List<TwitterData> latestTweet() {
        List<TwitterData> list = new ArrayList<>();
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status :statuses) {
                profileImageUrl =  status.getUser().getProfileImageURL();
                name = status.getUser().getName();
                twitterHandle =status.getUser().getScreenName();
                message = status.getText();
                createdAt = status.getCreatedAt();
                Format dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String date = dateFormat.format(createdAt);
                twitterData = new TwitterData(message,twitterHandle,name,profileImageUrl,date);
                list.add(twitterData);
            }
        } catch (TwitterException e) {
            logger.error("Error Occur", e);
             throw new RuntimeException("Run time error");
        }
        return list;
    }

    public List<TwitterData> filterTweet(String filter) throws IllegalArgumentException{
        List<TwitterData> list= latestTweet();
        int len = filter.length();
        CharSequence charSequence = filter.subSequence(0, len);
        List<TwitterData> filteredTweets = list.stream().filter(t -> t.getMessage().contains(charSequence)).collect(Collectors.toList());
        return filteredTweets;


    }
}
