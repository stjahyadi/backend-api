package com.singtel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.singtel.constant.Constant;
import com.singtel.domain.CommonResponse;
import com.singtel.domain.Person;
import com.singtel.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	@Qualifier(value = "getPersons")
	private List<Person> persons;

	@Override
	public CommonResponse find(String code) {
		CommonResponse resp = new CommonResponse();
		try{
			Person temp = persons.stream().filter(obj -> obj.getCode().equals(code)).findAny().orElse(null);
			if(temp != null){
				resp.setResult(temp);
				resp.setMessage("Success");
				resp.setCode(Constant.SUCCESSCODE);
			}else{
				resp.setMessage("Record Not Found");
				resp.setCode(Constant.FAILEDCODE);
			}
		}catch(Exception ex){
			resp.setMessage("Internal System Error!");
			resp.setCode(Constant.FAILEDCODE);
		}
		return resp;
	}

	@Override
	public CommonResponse get() {
		CommonResponse resp = new CommonResponse();
		if(!persons.isEmpty()){
			resp.setResult(persons);
			resp.setMessage("Success");
			resp.setCode(Constant.SUCCESSCODE);
		}else {
			resp.setCode(Constant.FAILEDCODE);
			resp.setMessage("No Records");
		}
		return resp;
	}

	@Override
	public CommonResponse add(Person person) {
		CommonResponse resp = new CommonResponse();
		if(person.getCode() != null){
			persons.add(person);
			persons.forEach(obj -> System.out.println(obj.getName() + " - " + obj.getAge() + " - " + obj.getPremium()));
			resp.setResult(person);
			resp.setMessage("Success");
			resp.setCode(Constant.SUCCESSCODE);
		}else{
			resp.setMessage("Failed");
			resp.setCode(Constant.FAILEDCODE);
		}
		return resp;
	}

	@Override
	public CommonResponse update(Person person) {
		CommonResponse resp = new CommonResponse();
		try{
			Person temp = persons.stream().filter(obj -> obj.getCode().equals(person.getCode())).findAny().orElse(null);
			if(temp != null){
				temp.setPremium(person.getPremium());
				resp.setResult(persons);
				resp.setMessage("Success");
				resp.setCode(Constant.SUCCESSCODE);
			}else{
				resp.setMessage("Record Not Found");
				resp.setCode(Constant.FAILEDCODE);
			}
		}catch(Exception ex){
			resp.setMessage("Internal System Error!");
			resp.setCode(Constant.FAILEDCODE);
		}
		return resp;
	}

	@Override
	public CommonResponse remove(String code) {
		CommonResponse resp = new CommonResponse();
		try{
			Person temp = persons.stream().filter(obj -> obj.getCode().equals(code)).findAny().orElse(null);
			if(temp != null){
				persons.remove(temp);
				resp.setResult(persons);
				resp.setMessage("Success");
				resp.setCode(Constant.SUCCESSCODE);
			}else{
				resp.setMessage("Record Not Found");
				resp.setCode(Constant.FAILEDCODE);
			}
		}catch(Exception ex){
			resp.setMessage("Internal System Error!");
			resp.setCode(Constant.FAILEDCODE);
		}
		return resp;
	}

	@Override
	public Person findByCode(String code) {
		Person temp = persons.stream().filter(obj -> obj.getCode().equals(code)).findAny().orElse(null);
		return temp;
	}
	
	
}
