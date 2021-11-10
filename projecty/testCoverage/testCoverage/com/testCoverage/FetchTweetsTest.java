package testCoverage.com.testCoverage;

import com.resource.resource.FetchTweets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class FetchTweetsTest {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    FetchTweets fetchTweets;
    Twitter twitter;

    @Before
    public void setUp() {
        configurationBuilder = mock(ConfigurationBuilder.class);
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        fetchTweets = new FetchTweets(configurationBuilder, twitterFactory);
    }

    @Test
    public void fetchTweetTest_successCase_listIsNotEmpty() throws TwitterException {

        Status s0 = mock(Status.class);
        Status s1 = mock(Status.class);
        Status s2 = mock(Status.class);
        when(s0.getText()).thenReturn("s0");
        when(s1.getText()).thenReturn("s1");
        when(s2.getText()).thenReturn("s2");
        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(3);
        when(responseList.get(0)).thenReturn(s0);
        when(responseList.get(1)).thenReturn(s1);
        when(responseList.get(2)).thenReturn(s2);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        Response actualTweet = fetchTweets.latestTweet();

        ArrayList<String> actualList = (ArrayList<String>) actualTweet.getEntity();
        Assert.assertEquals(Arrays.asList("s0", "s1", "s2"), actualList);
    }

    @Test
    public void fetchTweetTest_successCase_listIsEmpty() throws TwitterException {

        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(0);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        Response actualTweet = fetchTweets.latestTweet();

        ArrayList<String> actualList = (ArrayList<String>) actualTweet.getEntity();
        Assert.assertEquals(Arrays.asList("No Tweet Found "), actualList);
    }

    @Test
    public void fetchTweetTest_exceptionCase_throwsTwitterException() throws TwitterException {

        when(twitterFactory.getInstance()).thenReturn(twitter);
        when(twitter.getHomeTimeline()).thenThrow(TwitterException.class);
        String expectedError = "";
        try {
            fetchTweets.latestTweet();
        } catch (RuntimeException e) {
            expectedError = e.getMessage();
        }
        Assert.assertTrue(expectedError.equalsIgnoreCase("Run time error"));
    }

}