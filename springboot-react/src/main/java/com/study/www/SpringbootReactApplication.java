package com.study.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.study.www.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringbootReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootReactApplication.class, args);
	}

}
