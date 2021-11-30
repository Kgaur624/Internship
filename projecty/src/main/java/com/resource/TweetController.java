package com.resource;

import com.Model.SendResponse;
import com.Model.TwitterData;
import com.service.Services;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.Status;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class TweetController {
    Logger logger = LoggerFactory.getLogger(TweetController.class);;

    @Autowired
   Services services;

    @PostMapping("/sendTweet")
    public SendResponse sendTweet(@RequestBody PostReq postReq)  {
        try {
            String tweet = postReq.getMessage();
            if (StringUtils.isEmpty(tweet)) {
                logger.error("Field is empty");
                return new SendResponse("Field is empty",400);
            } else {
                Status status = services.sendTweets(tweet);
                if (status.getText().equals(tweet)) {
                    logger.info("Tweet posted Successfully");
                    return new SendResponse("Tweet posted Successfully",200);
                } else {
                    logger.error("Request incomplete");
                    return new SendResponse("Request incomplete",500);
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    @GetMapping("/getTimeline")
    public List<TwitterData> timeline() {
        return services.latestTweet();

    }


    @GetMapping("/filter")
    public List<TwitterData> filter(@QueryParam("searchKey") String searchKey) {
        return services.filterTweet(searchKey);
    }

}
