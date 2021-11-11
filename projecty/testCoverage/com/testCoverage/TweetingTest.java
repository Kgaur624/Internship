package com.testCoverage;
import com.resource.resource.PostReq;
import com.resource.resource.Tweeting;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TweetingTest {
    ConfigurationBuilder configurationBuilder ;
    TwitterFactory twitterFactory ;
    Tweeting tweeting;
    PostReq postReq;
    Twitter twitter;
    @Before
    public void setUp() throws Exception {
        configurationBuilder = mock(ConfigurationBuilder.class);
        twitterFactory = mock(TwitterFactory.class);
        postReq = new PostReq();
        twitter = mock(Twitter.class);
        tweeting = new Tweeting(configurationBuilder, twitterFactory);

    }

    @Test
    public void sendTweetTest() throws TwitterException {
        Status status ;
        Status s0 = mock(Status.class);
        postReq.setMessage("Kartik");
        String expectedTweet = postReq.getMessage();
        when(twitterFactory.getInstance()).thenReturn(twitter);
        when(twitter.updateStatus(expectedTweet)).thenReturn(s0);
        when(s0.getText()).thenReturn("Kartik");
        Status actualTweet = tweeting.sendTweets(expectedTweet);
        assertEquals(s0, actualTweet);

    }





}
