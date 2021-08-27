package com.twitter.demo.Controller;


import com.twitter.demo.Dtos.TweetsDto;
import com.twitter.demo.Services.TwitterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tweets")
public class TwitterController {

    private final TwitterService twitterService;

    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }


    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TweetsDto>> getAllTweetsFromTweet() throws TwitterException {
        List<TweetsDto> result = twitterService.getTweetsFromTweet();
        return ResponseEntity.ok().body(result);
    }
}
