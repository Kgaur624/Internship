package com.resource;


import org.apache.commons.lang3.StringUtils;
import twitter4j.TwitterException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TweetMain {
    @POST
    @Path("/sendTweet")
    public Response sendTweet(PostReq postReq) throws TwitterException {
        String tweets = postReq.getMessage();
        if(StringUtils.isEmpty(tweets)){
            return Response.status(400,"Field is empty").build();
        }
        else {
            Tweeting.sendTweets(tweets);
        }
        return Response.status(200,"Success").build();
    }


    @GET
    @Path("/getTimeline")
    public List<String> timeline() throws TwitterException
    {
         return FetchTweets.latestTweet();

    }
}