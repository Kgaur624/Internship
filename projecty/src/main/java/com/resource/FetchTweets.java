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

class FetchTweets {
    public static Response latestTweet() throws TwitterException {
        List<String> list = new ArrayList<String>();

        ConfigurationBuilder configurationBuilder = DropWizardConfiguration.getConfigurationObject();

        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = tf.getInstance();
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