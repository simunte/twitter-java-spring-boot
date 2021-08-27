package com.twitter.demo.Repository;

import com.twitter.demo.Domain.Tweets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetsRepository extends JpaRepository<Tweets, Long> {
}
