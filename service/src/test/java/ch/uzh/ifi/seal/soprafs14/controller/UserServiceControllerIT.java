package ch.uzh.ifi.seal.soprafs14.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ch.uzh.ifi.seal.soprafs14.controller.beans.JsonUriWrapper;
import ch.uzh.ifi.seal.soprafs14.controller.beans.user.UserRequestBean;
import ch.uzh.ifi.seal.soprafs14.controller.beans.user.UserResponseBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceControllerIT {

	final String TEST_SERVER = "http://localhost:26011";
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCreateUserSuccess() {
		List<UserResponseBean> results = new RestTemplate().getForObject(TEST_SERVER + "/user", List.class);
		assertThat(results.size(), is(0));
		
		UserRequestBean request = new UserRequestBean();
		request.setName("Mike Meyers");
		request.setUsername("mm");
		
		ResponseEntity<JsonUriWrapper> response = new RestTemplate().postForEntity(TEST_SERVER + "/user/", request, JsonUriWrapper.class);
		assertThat(response.getBody().getUri(), is("/user/1"));
		
		results = new RestTemplate().getForObject(TEST_SERVER + "/user", List.class);
		assertThat(results.size(), is(1));
	}
	

}
