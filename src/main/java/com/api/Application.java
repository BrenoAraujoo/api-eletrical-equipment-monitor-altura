package com.api;

import com.api.entities.ReadData;
import com.api.repositories.ReadDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Component
	public  class Runner implements ApplicationRunner{
		@Autowired
		ReadDataRepository repository;
		@Override
		public void run(ApplicationArguments args) throws Exception {


			ReadData data = new ReadData(null,200.00, Instant.parse("2022-08-03T16:08:50Z"));
			repository.save(data);
			System.out.println(data.getDateTime());



		}
	}

}
