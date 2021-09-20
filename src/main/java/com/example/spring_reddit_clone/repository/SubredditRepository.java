package com.example.spring_reddit_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.spring_reddit_clone.model.Subreddit;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit,Long>{

}
