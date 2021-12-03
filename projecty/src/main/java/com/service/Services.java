
/**
 * This package contains the Services.
 */
package com.service;
import com.Model.SendResponse;
import com.Model.TwitterData;
import com.config.DropWizardConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@CacheConfig(cacheNames ={"Tweets","filters"})
@Service
public class Services {
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Twitter twitter;
   TwitterData twitterData;

    /**
     * this constructor is used for test case
     *
     * @param twitterFactory
     */
    public Services(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
        this.twitter = this.twitterFactory.getInstance();
    }

    public Services(TwitterFactory twitterFactory, TwitterData twitterData) {
        this.twitterFactory = twitterFactory;
        this.twitterData=twitterData;
        this.twitter = this.twitterFactory.getInstance();
    }


    /**
     * this constructor is used for controller class
     */
    public Services() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DropWizardConfiguration.class);
        configurationBuilder=(ConfigurationBuilder)context.getBean("Configuration");
       twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }

    @Cacheable(cacheNames = {"Tweets"})
    @CacheEvict(cacheNames = {"Tweets"},allEntries = true)
    public Status sendTweets(String args) throws NullPointerException, TwitterException {
        List<SendResponse> list = new ArrayList<>();
        String message;
        String family;
        Status status = null;
        try {
            if (args.length() != 0)
                status = twitter.updateStatus(args);
            else
                status = null;
        } catch (Exception e) {
            if (args.length() > 280) {
                throw new TwitterException("Tweet needs to be a shorter");
            }
        }
        return status;
    }


    /**
     * latestTweet() used to get tweets from user timeline.
     *
     * @return returns tweets to controller class.
     */
    @Cacheable(cacheNames = {"Tweets"})
    @Scheduled(fixedRate = 2000)
    public List<TwitterData> latestTweet() {
        List<TwitterData> list = new ArrayList<>();
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            for (int i = 0; i < statuses.size(); i++) {
                Status s = statuses.get(i);
                String profileImageUrl = s.getUser().getProfileImageURL();
                String name = s.getUser().getName();
                String twitterHandle =s.getUser().getScreenName();
                String message = s.getText();
                Date createdAt = s.getCreatedAt();
                Format dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String date = dateFormat.format(createdAt);
                twitterData = new TwitterData(message,twitterHandle,name,profileImageUrl,date);
                list.add(twitterData);
            }
        } catch (TwitterException e) {
          throw new RuntimeException("Run time error");
        }
        return list;
    }


    /**
     * getFilteredTweets() used to get filtered tweets from user timeline.
     *
     * @param filter is used to search in a list of tweets.
     * @return returns filtered tweets.
     */
    @Cacheable(cacheNames = {"filters"})
    @Scheduled(fixedRate = 3000)
    public List<TwitterData> filterTweet(String filter) throws IllegalArgumentException{
        List<TwitterData> list= latestTweet();
        int len = filter.length();
        CharSequence charSequence = filter.subSequence(0, len);
        List<TwitterData> filteredTweets = list.stream().filter(t -> t.getMessage().contains(charSequence)).collect(Collectors.toList());
        return filteredTweets;


    }
}
