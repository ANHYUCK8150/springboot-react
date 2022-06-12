package com.study.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.study.www.config.AppProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(AppProperties.class)
public class SpringbootReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootReactApplication.class, args);
	}

	//	@Bean
	//    public WebMvcConfigurer corsConfigurer() {
	//        return new WebMvcConfigurer() {
	//            @Override
	//            public void addCorsMappings(CorsRegistry registry) {
	//                registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("*");
	//            }
	//        };
	//    }

}
