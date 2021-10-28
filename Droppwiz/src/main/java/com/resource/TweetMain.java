package com.resource;


import org.apache.commons.lang3.StringUtils;
import twitter4j.TwitterException;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TweetMain {
    @POST
    @Path("/tweet")
    public Response sendTweet(String s) throws TwitterException {
        if(StringUtils.isEmpty(s)){
            return Response.status(400,"Field is empty").build();

        }
        else {
            Tweeting.sendTweets(s);
        }
            return Response.status(200,"Success").build();

    }
    @GET
    @Path("/timeline")
    public String[] timeline() throws TwitterException
    {
        int size=RetrieveTweets.latestTweet().length;
        String ar[]=new String[size];
        int i=0;
        for(String s:RetrieveTweets.latestTweet())
        {
            ar[i]=s;
            i+=1;
        }
        return ar;
    }
}