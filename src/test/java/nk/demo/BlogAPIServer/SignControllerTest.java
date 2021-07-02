package nk.demo.BlogAPIServer;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * spring controller test 참고: https://tech.devgd.com/12 spring boot + test용 h2
 * db사용 + mybatis 참고:
 * https://atoz-develop.tistory.com/entry/Spring-Boot-MyBatis-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95
 * https://re-coder.tistory.com/5
 **/
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SignControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	void init() throws Exception {

		String jsonUserNormal = "{\"userId\":0,\"email\":\"gshgsh0831@gmail.com\",\"password\": \"Rnjs@123456789\",\"role\":null}";

		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content(jsonUserNormal)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	/**
	 * 로그인 성공 테스트
	 **/
	@Test
	public void signinSucessTest() throws Exception{

	}
	
	/**
	 * 로그인 실패 테스트 => 중복된 이메일로 로그인
	 **/
	@Test
	public void signinFailTest() throws Exception{

	}

	
	/**
	 * 회원가입 성공 테스트
	 * 
	 **/
	@Test
	public void signupSucessTest() throws Exception {
		String jsonUserNormal = "{\"userId\":0,\"email\":\"gshgsh1111@gmail.com\",\"password\": \"Rnjs@123456789\",\"role\":null}";

		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content(jsonUserNormal)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	/**
	 * 회원가입 실패 테스트 => 중복된 이메일로 회원가입
	 **/
	@Test
	public void signupFailTest() throws Exception{
		String jsonUserNormal = "{\"userId\":0,\"email\":\"gshgsh2222@gmail.com\",\"password\": \"Rnjs@123456789\",\"role\":null}";
		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content(jsonUserNormal)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
		
		jsonUserNormal = "{\"userId\":0,\"email\":\"gshgsh2222@gmail.com\",\"password\": \"Rnjs@123456789\",\"role\":null}";
		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content(jsonUserNormal)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.msg").value("email overlap"))
				.andDo(print());
	}

}
