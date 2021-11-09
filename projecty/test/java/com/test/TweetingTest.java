package com.test;
import com.config.DropWizardConfiguration;
import com.resource.PostReq;
import com.resource.Tweeting;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import org.mockito.MockedStatic;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetingTest {
    Tweeting tweeting;
    PostReq postReq;
    @Before
    public void setUp() throws Exception {
        tweeting = new Tweeting();
         postReq = new PostReq();

    }

    @Test
    public void sendTweetTest() throws TwitterException {
        postReq.setMessage("Kartik");
        String expectedTweet = postReq.getMessage();

        Status status = null ;
        try {
            status = tweeting.sendTweets(expectedTweet);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String actualTweet = status.getText();
        assertEquals(expectedTweet, actualTweet);

    }

    @Test
    public void NoTweetSend() {
        postReq.setMessage("");
        String expectedTweet = postReq.getMessage();
        Status status = tweeting.sendTweets(expectedTweet);
        int expectedLength = 0;
        int actuallength = 0;
        String actualTweet = "";
        if (status != null) {
            actualTweet = status.getText();
        }
        if (status == null) {
            expectedLength = expectedTweet.length();
            actuallength = 0;
        } else {
            expectedLength = expectedTweet.length();
            actuallength = actualTweet.length();
        }

        Assert.assertEquals(expectedLength, actuallength);
    }


}
