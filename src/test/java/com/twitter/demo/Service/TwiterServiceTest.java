package com.twitter.demo.Service;

import com.twitter.demo.Domain.Tweets;
import com.twitter.demo.Dtos.TweetsDto;
import com.twitter.demo.Properties.TwiterProperties;
import com.twitter.demo.Repository.TweetsRepository;
import com.twitter.demo.Services.Impl.TwitterServiceImpl;
import com.twitter.demo.Services.Mapper.TwitterMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TwiterProperties.class)
@TestPropertySource
public class TwiterServiceTest {

    @Autowired
    TwiterProperties classUsingProperty;

    @InjectMocks
    private TwitterServiceImpl twitterService;

    @Mock
    TwiterProperties mockEnvironment;

    @Mock
    private TweetsRepository tweetsRepository;

    @Mock
    private TwitterMapper twitterMapper;

    @Mock
    private Twitter twitter;

    @Before
    public void before() {
        Mockito.when(mockEnvironment.getApiKey()).thenReturn("6bMIUFw5XtmsYeMem0L3S2pN9");
        Mockito.when(mockEnvironment.getApiSecret()).thenReturn("EfYKEHP82Q9BGfu2HQAvLumw6BoFS1xC5DKI18YPBhJkRTDmjm");
        Mockito.when(mockEnvironment.getAccessToken()).thenReturn("1431209079184101379-Na8B5Gp1E2hFaXIxfQuWvDt5uFPcUK");
        Mockito.when(mockEnvironment.getAccessTokenSecret()).thenReturn("sVYWERSSbxnWnil8ixqez6bf1jhaMoft5r5qkNkwDWvCE");
    }


    @Test
    public void getTweetsFromTweet() throws TwitterException {
        Tweets tweets = new Tweets();
        tweets.setUser("user 1");
        tweets.setTweetId((long)1);
        tweets.setTweet("status 1");
        Tweets saved = tweets;
        saved.setId(1L);

        TweetsDto response = new TweetsDto();
        response.setUser(saved.getUser());
        response.setTweet(saved.getTweet());

        Mockito.when(tweetsRepository.save(saved)).thenReturn(saved);

        Mockito.when(twitterMapper.toDto(saved, new TweetsDto()))
                .thenReturn(response);


        List<TweetsDto> allTweet = twitterService.getTweetsFromTweeter();

        assertThat(!allTweet.isEmpty());
        assertThat(allTweet.size() > 0);
    }

    @Test
    public void getTwitterInstance(){

    }
}
