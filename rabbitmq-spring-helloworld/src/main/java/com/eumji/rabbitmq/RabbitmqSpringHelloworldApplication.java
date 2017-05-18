package com.eumji.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqSpringHelloworldApplication {

	@Profile("use_message")
	@Bean
	public CommandLineRunner runner(){
		return strings -> {
			System.out.println("This app uses Spring Profiles tocontrol its behavior.\n");
			System.out.println("Sample usage: java -jar rabbit-tutorials.jar --spring.profiles.active=hello-world,sender");
        };
	}

	@Profile("!use_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitAmqpTutorialsRunner();
	}
	public static void main(String[] args) {


		SpringApplication.run(RabbitmqSpringHelloworldApplication.class, args);
	}
}
