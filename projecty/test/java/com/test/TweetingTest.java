package com.test;
import com.config.DropWizardConfiguration;
import com.resource.PostReq;
import com.resource.Tweeting;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import org.mockito.MockedStatic;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetingTest {
    Tweeting tweeting;
    DropWizardConfiguration dropWizardConfiguration;
    PostReq postReq;
    MockedStatic<DropWizardConfiguration> dropWizardConfigurationMockitoStatic = Mockito.mockStatic(DropWizardConfiguration.class);
    @Before
    public void setUp() throws Exception {
        dropWizardConfiguration = Mockito.mock(DropWizardConfiguration.class);
         postReq = new PostReq();

    }

    @Test
    public void sendTweetTest() throws TwitterException {
        postReq.setMessage("Kartik");
        String expectedTweet = "KARTIK";
        dropWizardConfigurationMockitoStatic.when(DropWizardConfiguration::getConfigurationObject).thenReturn(new ConfigurationBuilder());

        String str = "KARTIK";
        when(postReq.getMessage()).thenReturn(str);
        Status status = tweeting.sendTweets(expectedTweet);
        String actualTweet = status.getText();
        assertEquals(expectedTweet, actualTweet);

    }

    @Test
    public void NoTweetSend() {
        dropWizardConfigurationMockitoStatic.when(DropWizardConfiguration::getConfigurationObject).thenReturn(new ConfigurationBuilder());

        postReq.setMessage("");
        String expectedTweet = postReq.getMessage();
        Status status = null;
        status = tweeting.sendTweets(expectedTweet);
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
