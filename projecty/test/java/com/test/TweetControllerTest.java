package com.test;

import com.resource.PostReq;
import com.resource.TweetController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import javax.ws.rs.core.Response;


@RunWith(MockitoJUnitRunner.class)
public class TweetControllerTest {
    TweetController tweetController;
    PostReq postReq;

    @Before
    public void setUp() {
        postReq = new PostReq();
        tweetController = new TweetController();
    }

    @Test
    public void sendTweet() {
        postReq.setMessage("kartik");
        Response responseActual = tweetController.sendTweet(postReq);
        System.out.println(responseActual);
    }
}