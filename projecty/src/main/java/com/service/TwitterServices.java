package com.service;

import com.resource.TweetController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;

public class TwitterServices {
    Tweeting tweeting;
    Logger logger;
    FetchTweets fetchTweets;

    public TwitterServices() {
       tweeting = new Tweeting();
       logger= LoggerFactory.getLogger(TweetController.class);
      fetchTweets = new FetchTweets();
    }

    public Response postTweet(String postReq) throws TwitterException {
         String tweet = postReq;
        if (StringUtils.isEmpty(tweet)) {
            logger.error("Field is empty");
            return Response.status(400, "Field is empty").build();
        } else {
            Status status = tweeting.sendTweets(tweet);
            if (status.getText().equals(tweet)) {
                logger.info("Tweet posted Successfully");
                return Response.status(200, "Tweet posted Successfully").build();
            } else {
                logger.error("Request incomplete");
                return Response.status(500, "Request incomplete").build();
            }
        }
    }


    public Response feeds() {
        return fetchTweets.latestTweet();
    }
}
