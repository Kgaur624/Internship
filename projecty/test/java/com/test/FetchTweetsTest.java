package com.test;

import com.config.DropWizardConfiguration;
import com.resource.FetchTweets;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FetchTweetsTest {
    DropWizardConfiguration dropWizardConfiguration;
    FetchTweets fetchTweets;
    MockedStatic<DropWizardConfiguration> dropWizardConfigurationMockitoStatic = Mockito.mockStatic(DropWizardConfiguration.class);
    Twitter twitter;
    @Before
    public void setUp() {
        dropWizardConfiguration = Mockito.mock(DropWizardConfiguration.class);
    }

    @Test
    public void fetchTweetTest() throws TwitterException {
        List<String> arrayList = new ArrayList<>();
        dropWizardConfigurationMockitoStatic.when(DropWizardConfiguration::getConfigurationObject).thenReturn( new ConfigurationBuilder());

        try {
            twitter = TwitterFactory.getSingleton();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                arrayList.add(status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        Response expectedTweet = Response.ok(arrayList).build();
        Response actualTweet = fetchTweets.latestTweet();
        Assert.assertEquals(expectedTweet.getLength(), actualTweet.getLength());
    }

    @Test
    public void fetchTweetTestNot() throws TwitterException {

        List<String> arrayList = new ArrayList<>();
        dropWizardConfigurationMockitoStatic.when(DropWizardConfiguration::getConfigurationObject).thenReturn( new ConfigurationBuilder());

        try {
            twitter = TwitterFactory.getSingleton();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                arrayList.add(status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        Response expectedTweet = Response.ok(arrayList).build();
        Response actualTweet = fetchTweets.latestTweet();
        Assert.assertNotEquals(expectedTweet.getLength(), actualTweet.getLength());
    }

    @Test
    public void fetchTweetTestLower() throws TwitterException {

        List<String> arrayList = new ArrayList<>();
        dropWizardConfigurationMockitoStatic.when(DropWizardConfiguration::getConfigurationObject).thenReturn( new ConfigurationBuilder());

        try {
            twitter = TwitterFactory.getSingleton();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                arrayList.add(status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        Response expectedTweet = Response.ok(arrayList).build();
        Response actualTweet = fetchTweets.latestTweet();
        Assert.assertEquals(expectedTweet.toString().toLowerCase()
                , actualTweet.toString().toLowerCase());
    }

    @Test
    public void fetchTweetTestUpper() throws TwitterException {

        List<String> arrayList = new ArrayList<>();

      // ConfigurationBuilder configurationBuilder = DropWizardConfiguration.getConfigurationObject();
        dropWizardConfigurationMockitoStatic.when(DropWizardConfiguration::getConfigurationObject).thenReturn( new ConfigurationBuilder());

        try {
            twitter = TwitterFactory.getSingleton();
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                arrayList.add(status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        Response expectedTweet = Response.ok(arrayList).build();
        Response actualTweet = fetchTweets.latestTweet();
        Assert.assertEquals(expectedTweet.toString().toLowerCase()
                , actualTweet.toString().toLowerCase());
    }

    @After
    public void close(){
        dropWizardConfigurationMockitoStatic.close();
    }


}