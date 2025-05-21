package com.github.gajicoding.schedule_api_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApiProjectApplication.class, args);
	}

}
