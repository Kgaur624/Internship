package test;

import com.config.DropWizardConfiguration;
import com.service.FetchTweets;
import com.service.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class FetchTweetsTest {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    FetchTweets fetchTweets;
    Twitter twitter;
    Service service;

    @Before
    public void setUp() {
        fetchTweets = new FetchTweets();
        configurationBuilder = mock(ConfigurationBuilder.class);
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        service = new Service(configurationBuilder,twitterFactory,twitter);
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
        List<Status> list = mock(List.class);
        when(list.size()).thenReturn(3);
        when(list.get(0)).thenReturn(s0);
        when(list.get(1)).thenReturn(s1);
        when(list.get(2)).thenReturn(s2);
        when(service.getTwitterInstance()).thenReturn(twitter);
        when(service.timeline()).thenReturn(list);
        List<String> actualTweet = fetchTweets.latestTweet();
        ArrayList<String> actualList = (ArrayList<String>) actualTweet;
        Assert.assertEquals(Arrays.asList("s0", "s1", "s2"), actualList);
    }

    @Test
    public void fetchTweetTest_successCase_listIsEmpty() throws TwitterException {
        List<Status> list = mock(List.class);
        when(list.size()).thenReturn(0);
        when(service.getTwitterInstance()).thenReturn(twitter);
        when(service.timeline()).thenReturn(list);
        List<String> actualTweet = fetchTweets.latestTweet();
        ArrayList<String> actualList = (ArrayList<String>) actualTweet;
        Assert.assertEquals(Arrays.asList("No Tweet Found "), actualList);
    }

    @Test
    public void fetchTweetTest_exceptionCase_throwsTwitterException() throws TwitterException{
        when(service.getTwitterInstance()).thenReturn(twitter);
        when(service.timeline()).thenThrow(TwitterException.class);
        String expectedError = "";
        try {
            fetchTweets.latestTweet();
        } catch (NullPointerException e) {
            expectedError = e.getMessage();
        }
        Assert.assertTrue(expectedError.equalsIgnoreCase("Run time error"));
    }

}

