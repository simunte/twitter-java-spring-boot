package com.twitter.demo.Dtos;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class TweetsDto {

    private String user;

    @Lob
    private String status;
}
