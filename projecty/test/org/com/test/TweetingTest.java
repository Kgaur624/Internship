import com.resource.resource.FetchTweets;
import com.resource.resource.PostReq;
import com.resource.resource.Tweeting;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TweetingTest {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Tweeting tweeting;
    PostReq postReq;
    @Before
    public void setUp() throws Exception {
        tweeting = new Tweeting();
         postReq = new PostReq();
        tweeting = new Tweeting(configurationBuilder, twitterFactory);

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
