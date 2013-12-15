package ch.uzh.ifi.seal.soprafs14.group_00_android.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestService {
	
	private final static String baseUrl = "http://10.0.2.2:8080/group-00-service/";
	private final static RestTemplate restTemplate;
	
	static {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
	}
	
	public static <T> ResponseEntity<T> get(String resource, Object requestData, Class<T> responseType) throws RestClientException {
    	return restTemplate.getForEntity(baseUrl + resource, responseType, requestData);
	}
	
	public static <T> ResponseEntity<T> post(String resource, Object requestData, Class<T> responseType) throws RestClientException {
		return restTemplate.postForEntity(baseUrl + resource, requestData, responseType);
	}
	
}
