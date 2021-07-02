package nk.demo.BlogAPIServer.Security.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import nk.demo.BlogAPIServer.CustomException.EmailSigninFailedException;
import nk.demo.BlogAPIServer.Response.CommonResult;
import nk.demo.BlogAPIServer.Response.ResponseService;
import nk.demo.BlogAPIServer.Response.SingleResult;
import nk.demo.BlogAPIServer.Security.JwtTokenProvider;

@Api(tags = { "2. 회원가입 & 로그인 API" })
@RequiredArgsConstructor
@RestController
public class SignController {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;

	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
	@PostMapping(value = "/signin")
	public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String email,
			@ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

		UserEntity userEntity = userRepository.getByEmail(email);
		if (!passwordEncoder.matches(password, userEntity.getPassword()))
			throw new EmailSigninFailedException("login fail, check email or password");

		return responseService.getSingleResult(
				jwtTokenProvider.createToken(String.valueOf(userEntity.getUserId()), userEntity.getRole()));
	}

	@ApiOperation(value = "가입", notes = "회원가입을 한다.")
	@PostMapping(value = "/signup")
	public CommonResult signup(@RequestBody UserEntity userEntity) {
		
		if(userRepository.getByEmail(userEntity.getEmail()) != null)
			throw new EmailSigninFailedException("email overlap");
		
		userRepository.save(
				UserEntity.builder()
							.email(userEntity.getEmail())
							.password(passwordEncoder.encode(userEntity.getPassword()))
							.role("ROLE_USER").build());
		return responseService.getSuccessResult();
	}
}