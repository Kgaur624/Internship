import ModelClass.TwitterData;
import com.resource.PostReq;
import com.service.Service;
import com.service.Tweeting;
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
    Service service;
    @Before
    public void setUp()  {

        configurationBuilder = mock(ConfigurationBuilder.class);
        twitterFactory = mock(TwitterFactory.class);
        postReq = new PostReq();
        twitter = mock(Twitter.class);
        tweeting = new Tweeting(service);
        twitterFactory = mock(TwitterFactory.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        service = new Service(twitterFactory);

    }

    @Test
    public void sendTweetTest_NotEmpty() throws TwitterException {
        postReq.setMessage("cart");
        Status s0 = mock(Status.class);
        String expectedTweet = postReq.getMessage();
        when(twitter.updateStatus(postReq.getMessage())).thenReturn(s0);
        Status actualTweet = service.sendTweets(expectedTweet);
        assertEquals(s0, actualTweet);
    }

@Test
    public void sendTweetTest_IsEmpty() throws TwitterException {
        postReq.setMessage("");
        Status s0 = twitter.updateStatus(postReq.getMessage());
        String expectedTweet = "";
        when(twitterFactory.getInstance()).thenReturn(twitter);
        when(twitter.updateStatus(postReq.getMessage())).thenReturn(s0);
        Status actualTweet = service.sendTweets(expectedTweet);
        assertEquals(s0, actualTweet);
    }





}
