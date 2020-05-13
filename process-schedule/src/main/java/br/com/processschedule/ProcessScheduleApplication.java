package br.com.processschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProcessScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessScheduleApplication.class, args);
	}

	
}
