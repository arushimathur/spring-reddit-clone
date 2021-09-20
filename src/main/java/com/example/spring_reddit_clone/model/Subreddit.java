package com.example.spring_reddit_clone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subreddit {

	 private static final String LAZY = null;
	 	@Id
	    @GeneratedValue(strategy = IDENTITY)
	    private Long id;
	    @NotBlank(message = "Community name is required")
	    private String name;
	    @NotBlank(message = "Description is required")
	    private String description;
	    @OneToMany(fetch = FetchType.LAZY)
	    private List<Post> posts;
	    private Instant createdDate;
	    @ManyToOne(fetch = FetchType.LAZY)
	    private User user;
}