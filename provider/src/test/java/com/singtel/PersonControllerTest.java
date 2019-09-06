package com.singtel;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.singtel.constant.Constant;
import com.singtel.domain.CommonResponse;
import com.singtel.domain.Person;
import com.singtel.service.PersonService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	@Autowired
	private PersonService personService;
	
	@LocalServerPort
	private int randomServerPort;

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPersons() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/get";
		URI uri = new URI(baseUrl);

		ResponseEntity<CommonResponse> result = restTemplate.getForEntity(uri, CommonResponse.class);
		List<Person> persons = (List<Person>) result.getBody().getResult();
		
		Assert.assertEquals(Constant.SUCCESSCODE, result.getBody().getCode());
		Assert.assertEquals(true, persons.size() > 0);
	}
	
	@Test
	public void testAddPersonSuccess() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/add";
		URI uri = new URI(baseUrl);
		
		Person person = new Person("A004", "Ani", 20, new BigDecimal(1000), "F");

		ResponseEntity<CommonResponse> result = restTemplate.postForEntity(uri, person, CommonResponse.class);
		
		Assert.assertEquals(Constant.SUCCESSCODE, result.getBody().getCode());
		Assert.assertEquals(true, result.getBody().getResult() != null);
	}
	
	@Test
	public void testAddPersonFailed() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/add";
		URI uri = new URI(baseUrl);
		
		Person person = new Person();

		ResponseEntity<CommonResponse> result = restTemplate.postForEntity(uri, person, CommonResponse.class);
		
		Assert.assertEquals(Constant.FAILEDCODE, result.getBody().getCode());
		Assert.assertEquals(true, result.getBody().getResult() == null);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindPersonSuccess() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/find/A001";
		URI uri = new URI(baseUrl);
		
		ResponseEntity<CommonResponse> result = restTemplate.getForEntity(uri, CommonResponse.class);
		Map<String, Object> payload = (Map<String, Object>) result.getBody().getResult();
		
		Assert.assertEquals(Constant.SUCCESSCODE, result.getBody().getCode());
		Assert.assertEquals("Sean", payload.get("name"));
	}
	
	@Test
	public void testFindPersonFailed() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/find/A000";
		URI uri = new URI(baseUrl);
		
		ResponseEntity<CommonResponse> result = restTemplate.getForEntity(uri, CommonResponse.class);
		
		Assert.assertEquals(Constant.FAILEDCODE, result.getBody().getCode());
		Assert.assertEquals(true, result.getBody().getResult() == null);
	}
	
	@Test
	public void testRemovePersonSuccess() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/remove/A001";
		URI uri = new URI(baseUrl);
		
		ResponseEntity<CommonResponse> result = restTemplate.getForEntity(uri, CommonResponse.class);
		
		Assert.assertEquals(Constant.SUCCESSCODE, result.getBody().getCode());
		Assert.assertEquals(true, personService.findByCode("A001") == null);
	}
	
	@Test
	public void testRemovePersonFailed() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/remove/A000";
		URI uri = new URI(baseUrl);
		
		ResponseEntity<CommonResponse> result = restTemplate.getForEntity(uri, CommonResponse.class);
		
		Assert.assertEquals(Constant.FAILEDCODE, result.getBody().getCode());
		Assert.assertEquals(true, personService.findByCode("A000") == null);
	}
	
	@Test
	public void testUpdatePersonFailed() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/update";
		URI uri = new URI(baseUrl);
		
		Person person = new Person();

		ResponseEntity<CommonResponse> result = restTemplate.postForEntity(uri, person, CommonResponse.class);
		
		Assert.assertEquals(Constant.FAILEDCODE, result.getBody().getCode());
		Assert.assertEquals(true, result.getBody().getResult() == null);
	}
	
	@Test
	public void testUpdatePersonSuccess() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/person/update";
		URI uri = new URI(baseUrl);
		
		Person person = personService.findByCode("A002");
		person.setPremium(new BigDecimal(5000));

		ResponseEntity<CommonResponse> result = restTemplate.postForEntity(uri, person, CommonResponse.class);
		
		Assert.assertEquals(Constant.SUCCESSCODE, result.getBody().getCode());
		Assert.assertEquals(new BigDecimal(5000), personService.findByCode("A002").getPremium());
	}
}
