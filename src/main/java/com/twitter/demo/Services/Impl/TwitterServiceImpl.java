package com.twitter.demo.Services.Impl;


import com.twitter.demo.Domain.Tweets;
import com.twitter.demo.Dtos.TweetsDto;
import com.twitter.demo.Repository.TweetsRepository;
import com.twitter.demo.Services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
//@Transactional
public class TwitterServiceImpl implements TwitterService{

    @Value("${twitter.apiKey}")
    private String apiKey;
    @Value("${twitter.apiSecret}")
    private String apiSecret;
    @Value("${twitter.accessToken}")
    private String accessToken;
    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Autowired
    private TweetsRepository tweetsRepository;

    @Override
    public List<TweetsDto> getTweetsFromTweet() throws TwitterException {
        Twitter twitter = getTwitterInstance();
        List<TweetsDto> listTweets = twitter.getHomeTimeline().stream()
                .map(status -> {
                    Tweets entity = new Tweets();
                    TweetsDto dto = new TweetsDto();
                    entity.setTweet(status.getText());
                    entity.setUser(status.getUser().getName());
                    dto.setUser(status.getUser().getName());
                    dto.setStatus(status.getText());
                    tweetsRepository.save(entity);
                    return dto;
                })
                .collect(Collectors.toCollection(LinkedList::new));
        return listTweets;
    }


    private Twitter getTwitterInstance(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(apiKey)
                .setOAuthConsumerSecret(apiSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        return twitter;
    }
}
