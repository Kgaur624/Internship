package com.service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.Response;
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

    /**
     * parameter constructor are using for unit testing
     * @param configurationBuilder
     * @param twitterFactory
     */
    public FetchTweets(ConfigurationBuilder configurationBuilder, TwitterFactory twitterFactory) {
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory = twitterFactory;
    }
    public  Response latestTweet() {
        List<String> list = new ArrayList<>();
        try {
            List<Status> statuses = service.timline();
            for (int i = 0; i < statuses.size(); i++) {
                list.add(statuses.get(i).getText());
            }
            if (list.isEmpty()) {
                logger.info("You Have No Tweets On your Timeline");
                list.add("No Tweet Found On TimeLine");
            }
            return Response.ok(list).build(); //  change this to return list
            } catch (TwitterException e) {
            logger.error("Error Occur",e);
            throw new RuntimeException("Run time error");
        }
    }
}