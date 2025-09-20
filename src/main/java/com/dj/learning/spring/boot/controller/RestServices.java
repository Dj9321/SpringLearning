package com.dj.learning.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dj.learning.spring.boot.config.MailConfigFromProperties;
import com.dj.learning.spring.boot.dto.RestTemplateExampleDto;
import com.dj.learning.spring.boot.entity.CheckTable;
import com.dj.learning.spring.boot.service.DataService;
import com.dj.learning.spring.boot.util.SpringCommonUtilFunctions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
public class RestServices {

	@Autowired
	DataService dataService;

	@Autowired
	SpringCommonUtilFunctions utilFunctions;

	@Autowired
	MailConfigFromProperties mailConfig;

	// Create here or create in @Configuration class as @Bean and autowire here
//	private RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/check")
	public String applicationCheck() {
		dataService.saveDataInCreatedByDB();
		return "Application is up and running";
	}

	@GetMapping("/jsonMapper")
	public String jsonMapper() throws JsonProcessingException {
		CheckTable entity = dataService.saveDataInCreatedByDB();
		// writing entity object to JSON
		ObjectMapper mapper = new ObjectMapper();
//		 mapper.readValue(src), classType);
		return mapper.writeValueAsString(entity);
	}

	@GetMapping("/callExternalWebsite")
	public String restTemplateExample() {
		System.out.println("===================== get For Object =====================");
		// 1. Using getForObject() > Directly maps to object
		String url = "https://jsonplaceholder.typicode.com/posts/2";
		RestTemplateExampleDto r = restTemplate.getForObject(url, RestTemplateExampleDto.class);
		System.out.println(r);

		System.out.println("===================== get For Entity =====================");
		// 2. Using getForEntity() > gives status code, headers, body (ResponseEntity>
		// If you keep String you get JSON, else you get the object directly (as
		// getForObject)
		ResponseEntity<String> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1",
				String.class);
		// Access response body, status, and headers
		System.out.println("Body: " + response.getBody());
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Headers: " + response.getHeaders());
		System.out.println();

		// 3. Using exchange() > More advanced and specific
		System.out.println("===================== Exchange() =====================");
		Gson gson = new Gson();
		String url1 = "https://jsonplaceholder.typicode.com/posts";
		// a. HttpHeaders
		HttpHeaders requestHeaders = new HttpHeaders();
//		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//		requestHeaders.set("uid", "234132513251325132534");
		String json = gson.toJson(response.getBody());

		// b. HttpEntity
		HttpEntity<String> entity = new HttpEntity<>(json, requestHeaders);
		// c. exchange()
		ResponseEntity<RestTemplateExampleDto> r1 = restTemplate.exchange(url1, HttpMethod.POST, entity,
				RestTemplateExampleDto.class);
		System.out.println(r1);
		System.out.println("Status Code: " + r1.getStatusCode());
		System.out.println("Headers: " + r1.getHeaders());
		return "See sysouts for more";
	}

	@GetMapping("/checkMailProperties")
	public String getMailProperties() {
		return mailConfig.getFrom() + " Host Name: " + mailConfig.getHostName() + " Port: " + mailConfig.getPort();
	}

	/**
	 * Check for /. Don't keep any slashes in between.
	 * "/dheeraj?requestWord=siramdas > shouldn't call /dheeraj/ {} with comma in
	 * between > accept both urls with and without the pathvariable
	 * @PathVariable: doesn't have defaultValue like @RequestParam
	 */
	@GetMapping({ "/callAssertCheck/", "/callAssertCheck/{word}" })
	public String callAssertCheck(@PathVariable(name = "word", required = false) String checkString,
			@RequestParam(required = false, name = "requestWord", defaultValue = "Default value is Dheeraj") String word1) {
		// @RequestParam, @PathParam. If you just give callAssertCheck/ without path
		// variable it doesn't work. If you give /abc/ > then you should call /abc/ OR
		// if /abc > call /abc (without ending /)
		System.out.println("Checking word in Request param " + word1);
		String stringUtils = utilFunctions.springStringUtils("Spring dj");
		return utilFunctions.springAssertFunctionality(checkString) + stringUtils;
	}

}
