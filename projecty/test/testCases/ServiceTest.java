import com.service.Service;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest extends TestCase {
    Service service;
    Twitter twitter;

    @Before
    public void setUp() {
        TwitterFactory twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        service = new Service(twitterFactory);
    }

    @Test
    public void testCase_latestTweet_success() throws TwitterException {
        // finish this test case Kathik
        ResponseList<Status> statuses = mock(ResponseList.class);
        when(twitter.getHomeTimeline()).thenReturn(statuses);
        service.latestTweet();
    }
}