package com.singtel;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.singtel.domain.Person;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8080/person/add",
				new Person("A004", "Ani", 20, new BigDecimal(1000), "F"), String.class);
		
		
		ResponseEntity<Person> p = restTemplate.getForEntity("http://localhost:8080/person/find/A001", Person.class);
		System.out.println("Result "+p.getBody());
	}
}
