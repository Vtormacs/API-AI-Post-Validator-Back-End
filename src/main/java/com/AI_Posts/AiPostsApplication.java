package com.AI_Posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AiPostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiPostsApplication.class, args);
	}

}
