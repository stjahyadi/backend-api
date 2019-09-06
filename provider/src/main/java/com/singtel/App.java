package com.singtel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.singtel.domain.Person;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	@Scope("singleton")
	public List<Person> getPersons(){
		List<Person> data = new ArrayList<Person>();
		data.add(new Person("A001", "Sean", 29, new BigDecimal(1000), "M"));
		data.add(new Person("A002", "Andy", 30, new BigDecimal(2000), "M"));
		data.add(new Person("A003", "Tom", 31, new BigDecimal(3000), "M"));
		return data;
		
	}
}
