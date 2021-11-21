
import ModelClass.TwitterData;
import com.service.FetchTweets;
import com.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchTweetsTest {

    TwitterFactory twitterFactory;
    FetchTweets fetchTweets;
    Twitter twitter;
    Service service;
    TwitterData twitterData;
    ResponseList responseList;
    Status s1;
    Status s2;
    Status s3;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String twitterHandle="@Kgaur624";
    String name="Kartik";
    String message="try";
    String profileImageUrl="www.KartikGour.com";
    Date created;
    String date;
    {
        try {
            created = dateFormat.parse("16-11-2021 01:03:00");
        } catch (ParseException E) {
            E.printStackTrace();
        }
        date = dateFormat.format(created);
    }

    @Before
    public void setUp() {
        twitter= mock(Twitter.class);
        s1=mock(Status.class);
        s2=mock(Status.class);
        s3=mock(Status.class);
        responseList=mock(ResponseList.class);
        fetchTweets=new FetchTweets(service);
         twitterFactory = mock(TwitterFactory.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterData = spy(new TwitterData(message, twitterHandle, name, profileImageUrl, date));
            twitterData = mock(TwitterData.class);
        service = new Service(twitterFactory,twitterData);
    }


    @Test
    public void fetchTweetTest_successCase_listIsNotEmpty() throws TwitterException {
        ResponseList<Status> list = mock(ResponseList.class);
        Status s0 = mock(Status.class);
        Status s1 = mock(Status.class);
        Status s2 = mock(Status.class);
        when(s0.getText()).thenReturn("s0");
        when(s1.getText()).thenReturn("s1");
        when(s2.getText()).thenReturn("s2");
        when(list.size()).thenReturn(3);
        when(list.get(0)).thenReturn(s0);
        when(list.get(1)).thenReturn(s1);
        when(list.get(2)).thenReturn(s2);
        when(twitter.getHomeTimeline()).thenReturn(list);
        List<TwitterData> actualTweet = service.latestTweet();
        //ArrayList<String> actualList = (ArrayList<String>) actualTweet;
        Assert.assertEquals(Arrays.asList("s0", "s1", "s2"), actualTweet);
    }

    @Test
    public void fetchTweetTest_successCase_listIsEmpty() throws TwitterException {

        ResponseList<Status> list = mock(ResponseList.class);
        when(list.size()).thenReturn(0);
        when(twitter.getHomeTimeline()).thenReturn( list);
        List<TwitterData> actualTweet = service.latestTweet();
        //ArrayList<String> actualList = (ArrayList<String>) actualTweet;
        Assert.assertEquals(Arrays.asList(""), actualTweet);
    }

    @Test(expected = RuntimeException.class)
    public void fetchTweetTest_exceptionCase_throwsTwitterException() throws TwitterException{
        when(twitter.getHomeTimeline()).thenThrow(TwitterException.class);
        String expectedError = "";
        try {
            service.latestTweet();
        } catch (NullPointerException e) {
            expectedError = e.getMessage();
        }
        Assert.assertTrue(expectedError.equalsIgnoreCase("Run time error"));
    }

    @Test
    public void testCase_fetchFilterTweet_successCase() throws TwitterException{
        Status s1 = mock(Status.class);
        User user=mock(User.class);
        ResponseList<Status> responseList = mock(ResponseList.class);
        List<TwitterData> expected=mock(ArrayList.class);
        when(responseList.size()).thenReturn(1);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getUser()).thenReturn(user);
        when(s1.getUser().getProfileImageURL()).thenReturn(profileImageUrl);
        when(s1.getUser().getName()).thenReturn(name);
        when(s1.getUser().getScreenName()).thenReturn(twitterHandle);
        when(s1.getText()).thenReturn(message);
        when(s1.getCreatedAt()).thenReturn(created);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        List<TwitterData> actualList = service.filterTweet("try");
        Assert.assertEquals(expected,actualList);
    }


}

