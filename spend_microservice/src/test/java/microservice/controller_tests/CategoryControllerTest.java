package microservice.controller_tests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
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
@WebMvcTest(value = Category.class, secure = false)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryController controller;

	@Test
	public void testJSONResponse() throws Exception {
		when(controller.getSuggestedCategories(anyString())).thenReturn(
			ResponseEntity.ok(Arrays.asList(new Category("newCategory1"), 
											new Category("newCategory2"), 
											new Category("newCategory3")))
		);
        
        Authorization authorization = AuthRequester.authenticate(
			"http://localhost:8080/users/authentication", 
			"zanferrari", 
			"zan12345");
        
		String expected = "[{\"category\":\"newCategory1\"},{\"category\":\"newCategory2\"},{\"category\":\"newCategory3\"}]";

        this.mockMvc.perform(
			get("/categories/suggestions")
				.param("partial_name", "cat")
				.header("Authorization", authorization.getAccessToken()))
			.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expected)
        );    	   
	}
	
	



}
