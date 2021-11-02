package com.resource;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

class FetchTweets {
    public static List<String> latestTweet() throws TwitterException {
        List<String> lst = new ArrayList<String>();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jUw2qaXkcqirbpR0h76dGQPEb")
                .setOAuthConsumerSecret("KlrnGLxD3QfQatAidusemOX8T7OL072Vi2HJppCMrxGL2svkcY")
                .setOAuthAccessToken("1450680649031962626-FlE4DbG86871LzJ6dVrA77aMjR47cy")
                .setOAuthAccessTokenSecret("gEIxm1eWf6ON47WszmZMzm6v9rtzWTo4LGJtIdygsJFeU");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<Status> statuses = twitter.getHomeTimeline();
        if (statuses.isEmpty()) {


            lst.add("No Tweet Found On TimeLine");


        } else {
            for (Status status1 : statuses) {
            lst.add(status1.getText());
            }
        }

        return lst;
    }
}