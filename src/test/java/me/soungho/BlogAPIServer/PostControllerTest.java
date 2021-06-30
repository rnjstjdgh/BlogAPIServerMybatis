package me.soungho.BlogAPIServer;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.soungho.BlogAPIServer.mvc.domain.Post;


//참고: https://tech.devgd.com/12
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getListTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/posts").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getTest() throws Exception {
		
		//정상 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/posts/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.postSeq").value("2"));
		
		//음수 값을 같는 게시글 번호로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/posts/-1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorMsg").value("postSeq cannot be minus."));
		
		//없는 게시글 번호로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/posts/99999").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorMsg").value("There is no corresponding information for postSeq."));
	}
	
	@Test
	public void saveTest() throws Exception{
		String jsonPost;
		Post post;
		// 정상 요청 => {title, user, contents}만 넘겼을 때
		post = new Post();
		post.setTitle("textTitle");
		post.setUser("testUser");
		post.setContents("testContent");
		jsonPost = objectMapper.writeValueAsString(post);
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.content(jsonPost)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		// 이상 요청 => content가 null일때
		post = new Post();
		post.setTitle("textTitle");
		post.setUser("testUser");
		jsonPost = objectMapper.writeValueAsString(post);
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.content(jsonPost)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"errorMsg\":\"Not enough post data.\"}"));
		
		// 이상 요청 => postSeq를 넘긴 경우
		post = new Post();
		post.setPostSeq(1);
		post.setTitle("textTitle");
		post.setUser("testUser");
		post.setContents("testContent");
		jsonPost = objectMapper.writeValueAsString(post);
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.content(jsonPost)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"errorMsg\":\"should not send postSeq.\"}"));
		
		// 이상 요청 => regDate를 넘긴 경우
		post = new Post();
		post.setRegDate(new Date());
		post.setTitle("textTitle");
		post.setUser("testUser");
		post.setContents("testContent");
		jsonPost = objectMapper.writeValueAsString(post);
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.content(jsonPost)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"errorMsg\":\"should not send regDate.\"}"));
	}
	
	@Test
	public void updateTest() throws Exception {
		String jsonPost;
		Post post;
		// 정상 요청 => {title, user, contents}만 넘겼을 때
		post = new Post();
		post.setTitle("textTitle");
		post.setUser("testUser");
		post.setContents("testContent");
		jsonPost = objectMapper.writeValueAsString(post);
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.content(jsonPost)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}
	
	
	
}
