package com.singtel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.singtel.domain.CommonResponse;
import com.singtel.domain.Person;
import com.singtel.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<Object> get() {
		CommonResponse resp = personService.get();
		return new ResponseEntity<Object>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody Person person) {
		CommonResponse resp = personService.add(person);
		return new ResponseEntity<Object>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/find/{code}", method = RequestMethod.GET)
	public ResponseEntity<Object> find(@PathVariable String code) {
		CommonResponse resp = personService.find(code);
		return new ResponseEntity<Object>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Object> find(@RequestBody Person person) {
		CommonResponse resp = personService.update(person);
		return new ResponseEntity<Object>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove/{code}", method = RequestMethod.GET)
	public ResponseEntity<Object> remove(@PathVariable String code) {
		CommonResponse resp = personService.remove(code);
		return new ResponseEntity<Object>(resp, HttpStatus.OK);
	}
}
