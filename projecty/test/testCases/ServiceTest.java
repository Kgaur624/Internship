import com.service.Services;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest extends TestCase {
    Services services;
    Twitter twitter;

    @Before
    public void setUp() {
        TwitterFactory twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        services = new Services(twitterFactory);
    }

    @Test
    public void testCase_latestTweet_success() throws TwitterException {
        ResponseList<Status> statuses = mock(ResponseList.class);
        when(twitter.getHomeTimeline()).thenReturn(statuses);
        services.latestTweet();
    }
}