package com.resource;

        import twitter4j.Status;
        import twitter4j.Twitter;
        import twitter4j.TwitterException;
        import twitter4j.TwitterFactory;
        import twitter4j.conf.ConfigurationBuilder;
        import java.util.List;
    class FetchTweets {
    public  static  String[] latestTweet() throws TwitterException {
        int size;
        String arr[] ;
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jUw2qaXkcqirbpR0h76dGQPEb")
                .setOAuthConsumerSecret("KlrnGLxD3QfQatAidusemOX8T7OL072Vi2HJppCMrxGL2svkcY")
                .setOAuthAccessToken("1450680649031962626-FlE4DbG86871LzJ6dVrA77aMjR47cy")
                .setOAuthAccessTokenSecret("gEIxm1eWf6ON47WszmZMzm6v9rtzWTo4LGJtIdygsJFeU");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<Status> statuses = twitter.getHomeTimeline();
       size = statuses.size();
        arr=new String[size];
        System.out.println("Showing home timeline.");
        int i=0;
        for (Status status1 : statuses)
        {
            arr[i]=status1.getText();
            i++;
        }
        if (size == 0){
            arr[0] = "No Tweet Found ";
        }
        return arr;
    }
    }