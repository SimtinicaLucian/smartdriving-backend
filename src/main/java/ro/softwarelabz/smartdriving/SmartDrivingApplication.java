package ro.softwarelabz.smartdriving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmartDrivingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartDrivingApplication.class, args);
	}

}
