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
        tweeting = new Tweeting();
       // tweeting = new Tweeting(configurationBuilder, twitterFactory);
        service = new Service(configurationBuilder,twitterFactory,twitter);
    }

    @Test
    public void sendTweetTest() throws TwitterException {

        Status s0 = mock(Status.class);
        postReq.setMessage("Kartik");
        String expectedTweet = postReq.getMessage();
        when(service.getTwitterInstance()).thenReturn(twitter);
        when(service.status()).thenReturn(s0);
        when(s0.getText()).thenReturn("Kartik");
        Status actualTweet = tweeting.sendTweets(expectedTweet);
        assertEquals(s0, actualTweet);

    }





}
