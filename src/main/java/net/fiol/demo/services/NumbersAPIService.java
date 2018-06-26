package net.fiol.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.fiol.demo.models.NumberTrivia;

@Service
public class NumbersAPIService {

	public NumberTrivia getYearTrivia(int year)
	{
		String url = new StringBuilder()
				.append("http://numbersapi.com/")
				.append(year)
				.append("/year?json")
				.toString();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<NumberTrivia> response = restTemplate.getForEntity(url, NumberTrivia.class);
				
		return response.getBody();
	}
}
