package com.resource;

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
    //DropWizardConfiguration dropWizardConfiguration;
//    public FetchTweets(DropWizardConfiguration dropWizardConfiguration){
//        this.dropWizardConfiguration=dropWizardConfiguration;
//    }
//    public  FetchTweets()
//    {
//
//    }
    public  Response latestTweet() throws TwitterException {

        List<String> list = new ArrayList<String>();
        DropWizardConfiguration dropWizardConfiguration = null;
        ConfigurationBuilder configurationBuilder = dropWizardConfiguration.getConfigurationObject();

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        List<Status> statuses = twitter.getHomeTimeline();
        for (Status status : statuses) {
            list.add(status.getText());
        }
        if (list.isEmpty()) {
            list.add("No Tweet Found ");
        }
        return Response.ok(list).build();
    }
}