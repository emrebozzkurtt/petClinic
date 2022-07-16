package com.emrebozzkurtt.petclinic.web;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.emrebozzkurtt.petclinic.model.Owner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PetClinicRestControllerTests {
	
	private TestRestTemplate restTemplate;
	
	@Before
	public void Setup() {
		restTemplate = restTemplate.withBasicAuth("ebozzkurtt", "147258");
		
		/*
		restTemplate = new RestTemplate();
		BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor("ebozzkurt", "147258");
		restTemplate.setInterceptors(Arrays.asList(interceptor));
		*/
	}
	
	@Test
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setFirstName("Ahmet");
		owner.setLastName("Kara");
		
		URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);
		
		Owner owner2 = restTemplate.getForObject(location, Owner.class);
		
		MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
		MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));
		
	}
	
	@Test
	public void testUpdateOwner() {
		Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);
		
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Cuma"));
		owner.setFirstName("Berkvycan");
		restTemplate.put("http://localhost:8080/rest/owner/3", owner);
		
		owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("cumaTest"));
	}
	

	@Test
	public void testDeleteOwner() {
		//restTemplate.delete("http://localhost:8080/rest/owner/3");
		ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:8080/rest/owner/3", HttpMethod.DELETE, null, Void.class);
		try {
			restTemplate.getForEntity("http://localhost:8080/rest/owner/3", Owner.class);
			Assert.fail("should have not returned owner");
		} catch (HttpClientErrorException ex) {
			MatcherAssert.assertThat(ex.getStatusCode(), Matchers.equalTo(404));
		}
	}
	
	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
		
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Ziya"));
	}
	
	@Test
	public void testGetOwnerByLastName() {
		ResponseEntity<List> owners = restTemplate.getForEntity("http://localhost:8080/rest/owner?lastName=Bozkurt", List.class);
		
		MatcherAssert.assertThat(owners.getStatusCodeValue(), Matchers.equalTo(200));
		
		@SuppressWarnings("unchecked")
		List<Map<String, String>> body = owners.getBody();
		
		List<String> firtNames = body.stream().map(o->o.get("firstName")).collect(Collectors.toList());
		
		MatcherAssert.assertThat(firtNames, Matchers.containsInAnyOrder("Emre","Mustafa","Cuma","Berkay"));
	}

	@Test
	public void testGetOwners() {
		ResponseEntity<List> owners = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
		
		MatcherAssert.assertThat(owners.getStatusCodeValue(), Matchers.equalTo(200));
		
		@SuppressWarnings("unchecked")
		List<Map<String, String>> body = owners.getBody();
		
		List<String> firtNames = body.stream().map(o->o.get("firstName")).collect(Collectors.toList());
		
		MatcherAssert.assertThat(firtNames, Matchers.containsInAnyOrder("Emre","Mustafa","Cuma","Berkay"));
	}
	
}
