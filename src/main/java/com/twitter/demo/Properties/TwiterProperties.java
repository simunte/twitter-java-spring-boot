package com.twitter.demo.Properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitter")
@Data
public class TwiterProperties {
    private String apiKey;

    private String apiSecret;

    private String accessToken;

    private String accessTokenSecret;

}
