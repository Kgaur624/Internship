package com.resource;

import com.service.FetchTweets;
import com.service.Tweeting;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TweetController {
    Tweeting tweeting = new Tweeting();
    Logger logger = LoggerFactory.getLogger(TweetController.class);;
    FetchTweets fetchTweets = new FetchTweets();

    @POST
    @Path("/sendTweet")
    public Response sendTweet(PostReq postReq) throws TwitterException {
        String tweet = postReq.getMessage();
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

    @GET
    @Path("/getTimeline")
    public List<String> timeline() {
        return fetchTweets.latestTweet();
    }
}