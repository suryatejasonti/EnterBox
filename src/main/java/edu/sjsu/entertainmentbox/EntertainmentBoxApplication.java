package edu.sjsu.entertainmentbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class EntertainmentBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntertainmentBoxApplication.class, args);
	}

	/**
	 * Required t inject properties using 'Value' annotation
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
