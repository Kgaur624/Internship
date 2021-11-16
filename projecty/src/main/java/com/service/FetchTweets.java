package com.service;
import org.checkerframework.checker.units.qual.C;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class FetchTweets {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Logger logger= LoggerFactory.getLogger(FetchTweets.class);
    Service service;


    public FetchTweets() {
         service = new Service();
        configurationBuilder = service.configuration();
        twitterFactory = service.twitterFactory();
    }
    public FetchTweets(Service service){
        this.service=service;
        configurationBuilder = service.configuration();
        twitterFactory = service.twitterFactory();
    }


    public FetchTweets(ConfigurationBuilder configurationBuilder, TwitterFactory twitterFactory) {
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory = twitterFactory;
    }
    public  List<String> latestTweet() {
        List<String> list = new ArrayList<>();
        try {
            List<Status> statuses = service.timeline();
            for (Status status : statuses) {
                list.add(status.getText());
            }
            if (list.isEmpty()) {
                logger.info("You Have No Tweets On your Timeline");
                list.add("No Tweet Found On TimeLine");
            }
           // return list; //  change this to return list
            } catch (TwitterException e) {
            logger.error("Error Occur",e);
          //  throw new RuntimeException("Run time error");
        }
        return list;
    }
}