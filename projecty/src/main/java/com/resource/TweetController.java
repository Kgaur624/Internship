package com.resource;

import com.Model.SendResponse;
import com.Model.TwitterData;
import com.service.Services;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class TweetController {
    Logger logger = LoggerFactory.getLogger(TweetController.class);

    @Autowired
    Services services;

    @PostMapping("/sendTweet")
    public ResponseEntity<SendResponse> sendTweet(@RequestBody PostReq postReq) {
        try {
            String tweet = postReq.getMessage();
            if (StringUtils.isEmpty(tweet)) {
                logger.error("Field is empty");
                return new ResponseEntity(new SendResponse("Field is empty"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
            } else {
                Status status = services.sendTweets(tweet);
                if (status.getText().equals(tweet)) {
                    logger.info("Tweet posted Successfully");
                    return new ResponseEntity(new SendResponse("Successfully posted tweet!"), new HttpHeaders(), HttpStatus.OK);

                } else {
                    logger.error("Request incomplete");

                    return new ResponseEntity(new SendResponse("Resquest Incomplete"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

                }
            }
        } catch (TwitterException exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Request Incomplete", exc);
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
