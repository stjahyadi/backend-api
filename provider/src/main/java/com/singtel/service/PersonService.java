package com.singtel.service;


import com.singtel.domain.CommonResponse;
import com.singtel.domain.Person;

public interface PersonService {

	public CommonResponse find(String code);
	
	public CommonResponse get();
	
	public CommonResponse add(Person person);
	
	public CommonResponse update(Person person);
	
	public CommonResponse remove(String code);
	
	public Person findByCode(String code);
}
