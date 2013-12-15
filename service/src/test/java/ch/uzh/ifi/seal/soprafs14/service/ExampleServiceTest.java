package ch.uzh.ifi.seal.soprafs14.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.uzh.ifi.seal.soprafs14.service.ExampleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test.xml"})
public class ExampleServiceTest {

	@Autowired
	ExampleService exampleService;
	
	@Test
	public void testDoLogic() {
		assertThat(exampleService.doLogic("a", "b"), is("ab"));
	}
}
