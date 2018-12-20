package microservice.controller_tests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import microservice.controllers.CategoryController;
import microservice.models.Authorization;
import microservice.models.Category;
import microservice.util.AuthRequester;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoryController.class, secure = false)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryController controller;

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testJSONResponse() throws Exception {
		List<Category> mockCategories = Arrays.asList(new Category("5c1afa52dd3b7e2268264e9d", "dummyCategory1"), 
														new Category("5c1afa82dd3b7e2268264e9f", "dummyCategory2"), 
														new Category("5c1aff16dd3b7e2698e06a1e", "dummyCategory3"));

		when(controller.getSuggestedCategories(anyString())).thenReturn(
			ResponseEntity.ok(mockCategories)
		);
        
        Authorization authorization = AuthRequester.authenticate(
			"http://localhost:8080/users/authentication", 
			"zanferrari", 
			"zan12345");

		String expected = mapper.writeValueAsString(mockCategories);

        this.mockMvc.perform(
			get("/categories/suggestions")
				.param("partial_name", "cat")
				.header("Authorization", authorization.getAccessToken()))
			.andDo(print())
            .andExpect(status().isOk())
			.andExpect(content().json(expected))
            .andExpect(content().string(expected));
	}
	

	
}
