package microservice.controller_tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.net.URI;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import microservice.controllers.SpendController;
import microservice.models.Authorization;
import microservice.models.Spend;
import microservice.util.AuthRequester;
import org.springframework.web.util.UriComponentsBuilder;
import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@WebMvcTest(value = SpendController.class, secure = false)
public class SpendControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private SpendController controller;
    
    private final ObjectMapper mapper = new ObjectMapper();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    
    @Test
	public void testInsertSpendJSONResponse() throws Exception {
        String mockDateStr = "2018-12-20T02:32:13.743+0000";
        Date mockDate = dateFormat.parse(mockDateStr);

        Spend mockSpend = new Spend();
        mockSpend.set_id("5c1af62cdd3b7e2014b8b5a4");
        mockSpend.setDescription("dummyDescription");
        mockSpend.setUserCode("5c171d4ba917193e00cf68c4");
        mockSpend.setValue(new BigDecimal(123.456));
        mockSpend.setCategory("dummyCategory");
        mockSpend.setDate(mockDate);

        ResponseEntity<Spend> mockCreatedResponse = ResponseEntity.created(new URI("http://localhost:8081/spend/5c17215ea917193e0c3c84ab")).body(mockSpend);

		when(controller.register(any(UriComponentsBuilder.class), any(Spend.class))).thenReturn(mockCreatedResponse);
        
        Authorization authorization = AuthRequester.authenticate(
			"http://localhost:8080/systems/authentication", 
			"mySystem", 
            "54321naz");
            
        String expected = mapper.writeValueAsString(mockSpend);

        String content = "{\"description\":\"dummyDescription\",\"value\":123.456,\"userCode\":\"5c171d4ba917193e00cf68c4\",\"category\":\"dummyCategory\",\"date\":\"2018-12-20T02:32:13.743+0000\"}";

        this.mockMvc.perform(
            post("/spends")
                .header("Authorization", authorization.getAccessToken())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().json(expected))
            .andExpect(content().string(expected));

	}

    @Test
	public void testListUserSpendsJSONResponse() throws Exception {
        String mockDateStr = "2018-12-18T02:48:26.163+0000";
        String startDateStr = "2018-12-15T02:48:26.163+0000";
        String endDateStr = "2018-12-20T21:51:33.775+0000";

        Date mockDate = dateFormat.parse(mockDateStr);

        Spend firstSpend = new Spend("5c1afa52dd3b7e2268264e9d", "dummyDescription1", new BigDecimal(123.465), "5c17214ea917193e0c3c84aa", "dummyCategory1", mockDate);
        Spend secondSpend = new Spend("5c1afbb5dd3b7e2268264ead", "dummyDescription2", new BigDecimal(879.1011), "5c1aff16dd3b7e2698e06a1e", "dummyCategory2", mockDate);
        Spend thirdSpend = new Spend("5c1aff16dd3b7e2698e06a1e", "dummyDescription3", new BigDecimal(1213.1415), "5c1aff42dd3b7e2698e06a22", "dummyCategory3", mockDate);

		List<Spend> mockSpends = Arrays.asList(firstSpend, secondSpend, thirdSpend);

        when(controller.getUserSpends(any(HttpServletRequest.class), anyString(), anyString()))
        .thenReturn(ResponseEntity.ok(mockSpends));
        
        Authorization authorization = AuthRequester.authenticate(
			"http://localhost:8080/users/authentication", 
			"zanferrari", 
			"zan12345");

		String expected = mapper.writeValueAsString(mockSpends);

        this.mockMvc.perform(
            get("/users/spends")
                .param("start_date", startDateStr)
                .param("end_date", endDateStr)
				.header("Authorization", authorization.getAccessToken()))
			.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expected))
            .andExpect(content().string(expected));
   
	}
}
