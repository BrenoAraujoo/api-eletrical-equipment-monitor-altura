package com.api;

import com.api.entities.ReadData;
import com.api.repositories.ReadDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

		@Value("${password}")
		String msg;
		@Override
		public void run(ApplicationArguments args) throws Exception {
			System.out.println(msg);
		}

	}

}
