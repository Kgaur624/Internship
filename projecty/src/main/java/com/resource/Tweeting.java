package com.resource;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;

class Tweeting {
    public static void sendTweets(String args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jUw2qaXkcqirbpR0h76dGQPEb")
                .setOAuthConsumerSecret("KlrnGLxD3QfQatAidusemOX8T7OL072Vi2HJppCMrxGL2svkcY")
                .setOAuthAccessToken("1450680649031962626-FlE4DbG86871LzJ6dVrA77aMjR47cy")
                .setOAuthAccessTokenSecret("gEIxm1eWf6ON47WszmZMzm6v9rtzWTo4LGJtIdygsJFeU");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Status status = null;
        try {
            status = twitter.updateStatus(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Successfully updated the status to [" + status.getText() + "].");

    }
}



