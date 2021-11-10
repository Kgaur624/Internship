package com.resource.resource;

import org.apache.commons.lang3.StringUtils;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TweetController {
    Tweeting tweeting = new Tweeting();

    @POST
    @Path("/sendTweet")
    public Response sendTweet(PostReq postReq)  {
        String tweet = postReq.getMessage();
        if (StringUtils.isEmpty(tweet)) {
            return Response.status(400, "Field is empty").build();
        } else {
            Status status = tweeting.sendTweets(tweet);
            if (status.getText().equals(tweet)) {
                return Response.status(200, "Tweet posted Successfully").build();
            } else {
                return Response.status(500, "Request incomplete").build();
            }
        }

    }


    @GET
    @Path("/getTimeline")
    public Response timeline() throws TwitterException {
        FetchTweets fetchTweets = new FetchTweets();
        return fetchTweets.latestTweet();

    }
}