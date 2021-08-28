package com.twitter.demo.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tweets_data")
public class Tweets{
    private static final long serialVersionUID = -7369920601847524273L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Long tweetId;

    @Lob
    private String tweet;

    private String user;

    @CreatedDate
    @Column(name = "date_created", updatable = false)
    private Instant creationDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = Instant.now();
    }
}
