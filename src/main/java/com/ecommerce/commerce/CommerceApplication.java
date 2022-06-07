package com.ecommerce.commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CommerceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommerceApplication.class, args);


	}
	@Bean
	public WebMvcConfigurer corsConfig(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://0.0.0.0:3000","http://localhost:3000").allowedMethods(
						"PUT","GET","POST","DELETE"
				).maxAge(89999);
			}
		};
	}
}
