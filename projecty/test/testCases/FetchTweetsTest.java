
import com.service.FetchTweets;
import com.service.*;
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
    ResponseList responseList;
    Status s1;
    Status s2;
    Status s3;

    @Before
    public void setUp() {
        twitter= mock(Twitter.class);
        service= mock(Service.class);
        s1=mock(Status.class);
        s2=mock(Status.class);
        s3=mock(Status.class);
        responseList=mock(ResponseList.class);
        fetchTweets=new FetchTweets(service);

    }


    @Test
    public void fetchTweetTest_successCase_listIsNotEmpty() throws TwitterException {

        Status s0 = mock(Status.class);
        Status s1 = mock(Status.class);
        Status s2 = mock(Status.class);
        when(s0.getText()).thenReturn("s0");
        when(s1.getText()).thenReturn("s1");
        when(s2.getText()).thenReturn("s2");
        ResponseList<Status> list = mock(ResponseList.class);
        when(list.size()).thenReturn(3);
        when(list.get(0)).thenReturn(s0);
        when(list.get(1)).thenReturn(s1);
        when(list.get(2)).thenReturn(s2);
        when(twitter.getHomeTimeline()).thenReturn(list);
        List<String> actualTweet = service.latestTweet();
        ArrayList<String> actualList = (ArrayList<String>) actualTweet;
        Assert.assertEquals(Arrays.asList("s0", "s1", "s2"), actualList);
    }

    @Test
    public void fetchTweetTest_successCase_listIsEmpty() throws TwitterException {

        List<Status> list = mock(List.class);
        when(list.size()).thenReturn(0);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        when(twitter.getHomeTimeline()).thenReturn((ResponseList<Status>) list);
        List<String> actualTweet = service.latestTweet();
        ArrayList<String> actualList = (ArrayList<String>) actualTweet;
        Assert.assertEquals(Arrays.asList(), actualList);
    }

    @Test
    public void fetchTweetTest_exceptionCase_throwsTwitterException() throws TwitterException{
        when(twitterFactory.getInstance()).thenReturn(twitter);
        when(twitter.getHomeTimeline()).thenThrow(TwitterException.class);
        String expectedError = "";
        try {
            service.latestTweet();
        } catch (NullPointerException e) {
            expectedError = e.getMessage();
        }
        Assert.assertTrue(expectedError.equalsIgnoreCase("Run time error"));
    }

}

