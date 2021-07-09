package com.tweet.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweet.app.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, String> {
}
