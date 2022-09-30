package com.api;

import com.api.entities.enums.MeasurementType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		for(MeasurementType t: MeasurementType.values()){
			System.out.println(" ---- >" + t.getCode());
		}
	}
}


