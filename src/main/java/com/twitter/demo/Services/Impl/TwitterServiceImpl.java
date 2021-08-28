package com.twitter.demo.Services.Impl;


import com.twitter.demo.Domain.Tweets;
import com.twitter.demo.Dtos.TweetsDto;
import com.twitter.demo.Properties.TwiterProperties;
import com.twitter.demo.Repository.TweetsRepository;
import com.twitter.demo.Services.Mapper.TwitterMapper;
import com.twitter.demo.Services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
//@Transactional
public class TwitterServiceImpl implements TwitterService{

    @Autowired
    private TweetsRepository tweetsRepository;

    @Autowired
    TwiterProperties twiterProperties;

    @Transactional
    @Override
    @Scheduled(cron = "${scheduling.cron.tweet}")
    public List<TweetsDto> getTweetsFromTweeter() throws TwitterException {
        Twitter twitter = getTwitterInstance();
        List<TweetsDto> listTweets = twitter.getHomeTimeline().stream()
                .map(status -> {
                    Optional<Tweets> existEntity= tweetsRepository.findTopByTweetIdOrderByCreationDateDesc(status.getId());
                    Tweets newEntity = null;
                    if (existEntity.isPresent()){
                        existEntity.get().setTweetId(existEntity.get().getId());
                        existEntity.get().setTweet(status.getText());
                        existEntity.get().setUser(status.getUser().getName());
                        newEntity = tweetsRepository.save(existEntity.get());
                    }else {
                        Tweets entity = new Tweets();
                        entity.setTweetId(status.getId());
                        entity.setTweet(status.getText());
                        entity.setUser(status.getUser().getName());
                        newEntity = tweetsRepository.save(entity);
                    }
                    return TwitterMapper.INSTANCE.toDto(newEntity, new TweetsDto());
                })
                .collect(Collectors.toCollection(LinkedList::new));

        return listTweets;
    }


    public Twitter getTwitterInstance(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(twiterProperties.getApiKey())
                .setOAuthConsumerSecret(twiterProperties.getApiSecret())
                .setOAuthAccessToken(twiterProperties.getAccessToken())
                .setOAuthAccessTokenSecret(twiterProperties.getAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        return twitter;
    }

}
