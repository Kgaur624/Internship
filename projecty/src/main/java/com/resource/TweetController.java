package com.resource;

import com.service.TwitterServices;
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
    TwitterServices twitterServices = new TwitterServices();

    @POST
    @Path("/sendTweet")
    public Response sendTweet(PostReq postReq) throws TwitterException {
    return twitterServices.postTweet(postReq.getMessage());
    }

    @GET
    @Path("/getTimeline")
    public Response timeline() {
       return twitterServices.feeds();
    }
}