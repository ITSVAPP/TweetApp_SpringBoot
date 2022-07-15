package com.tweet.app.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tweet.app.service.TweetService;

@SpringBootTest
class MainControllerTest {

	MockMvc mockMvc;

	@Autowired
	MainController target;

	@MockBean
	TweetService tweetService;

	@BeforeEach
	void setup() {
		mockMvc = standaloneSetup(target).build();
	}

//	@Test
//	void test() throws Exception {
//		when(tweetService.findAll()).thenReturn(Collections.singletonList(new Tweet(0, null, null, null, null)));
//		mockMvc
//				.perform(get("/"))
//				// HTTPステータスがOKであること確認
//				.andExpect(status().isOk())
//				// modelにエラーがないことを確認
//				.andExpect(model().hasNoErrors())
//				.andExpect(model().attribute("tweetList",
//						Collections.singletonList(new Tweet(0, null, null, null, null))));
//	}

}
