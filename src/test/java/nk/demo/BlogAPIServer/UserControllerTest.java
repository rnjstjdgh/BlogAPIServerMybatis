package nk.demo.BlogAPIServer;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * spring controller test 참고: https://tech.devgd.com/12 spring boot + test용 h2
 * db사용 + mybatis 참고:
 * https://atoz-develop.tistory.com/entry/Spring-Boot-MyBatis-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95
 * https://re-coder.tistory.com/5
 **/
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)	//@Test가 붙은 메소드를 실행할 때 마다가 아니라 모든 테스트에 대해 하나의 인스턴스만 만든다.
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

}
