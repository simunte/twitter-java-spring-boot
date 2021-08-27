package com.twitter.demo.Services;

import twitter4j.TwitterException;

import java.util.List;

public interface TwitterService {
    List<String> getTweetsFromTweet() throws TwitterException;
}
