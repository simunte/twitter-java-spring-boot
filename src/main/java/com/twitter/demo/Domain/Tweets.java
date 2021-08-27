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
public class Tweets extends Base{

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
