package com.twitter.demo.Services;

import com.twitter.demo.Dtos.TweetsDto;
import twitter4j.TwitterException;

import java.util.List;

public interface TwitterService {
    List<TweetsDto> getTweetsFromTweet() throws TwitterException;
}
