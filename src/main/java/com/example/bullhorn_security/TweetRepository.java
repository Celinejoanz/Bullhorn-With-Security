package com.example.bullhorn_security;

import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet, Long> {
}