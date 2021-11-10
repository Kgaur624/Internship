package com.resource.resource;

import com.config.DropWizardConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class FetchTweets {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;

    public FetchTweets() {
        configurationBuilder = DropWizardConfiguration.getConfigurationObject();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
    }

    public FetchTweets(ConfigurationBuilder configurationBuilder, TwitterFactory twitterFactory) {
        this.configurationBuilder = configurationBuilder;
        this.twitterFactory = twitterFactory;
    }

    public  Response latestTweet() {
        List<String> list = new ArrayList<>();
        Twitter twitter = twitterFactory.getInstance();
        try {
            List<Status> statuses = twitter.getHomeTimeline();

            for (int i = 0; i < statuses.size(); i++) {
                list.add(statuses.get(i).getText());
            }
            if (list.isEmpty()) {
                list.add("No Tweet Found ");
            }
            return Response.ok(list).build(); //  change this to return list
        } catch (TwitterException e) {
            System.out.println(e);
            // create a custom exception
            throw new RuntimeException("Run time error");
        }
    }
}