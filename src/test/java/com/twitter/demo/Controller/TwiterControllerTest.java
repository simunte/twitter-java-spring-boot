package com.twitter.demo.Controller;

import com.twitter.demo.DemoApplication;
import com.twitter.demo.Dtos.TweetsDto;
import com.twitter.demo.Services.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class TwiterControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private TwitterService twitterService;

    @Test
    public void getAllTweetsFromTweet() throws Exception{
        Mockito.when(twitterService.getTweetsFromTweeter()).thenReturn(Arrays.asList(
                new TweetsDto("user 1", "status 1"),
                new TweetsDto("user 2", "status 2")
        ));

        mockMvc.perform(get("/api/v1/tweets/get/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"));

    }

    @Test
    public void getAllTweetsFromTweetNoData() throws Exception{
        Mockito.when(twitterService.getTweetsFromTweeter()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/tweets/get/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

    }

}
