package com.example.yuki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class YukiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YukiApplication.class, args);
	}
}
