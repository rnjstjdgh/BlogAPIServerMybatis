package nk.demo.BlogAPIServer.Security.Sign;

import nk.demo.BlogAPIServer.CustomException.CustomNullPointException;
import nk.demo.BlogAPIServer.Security.User.UserEntity;
import nk.demo.BlogAPIServer.Security.User.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nk.demo.BlogAPIServer.Response.CommonResult;
import nk.demo.BlogAPIServer.Response.ResponseService;
import nk.demo.BlogAPIServer.Response.SingleResult;
import nk.demo.BlogAPIServer.Security.JWT.JwtTokenProvider;

@Api(tags = { "2. 회원가입 & 로그인 API" })
@RequiredArgsConstructor
@RestController
public class SignController {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;
	private final SignService signService;

	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
	@PostMapping(value = "/signin")
	public SingleResult<String> signin(@RequestBody UserEntity userEntity) {
		if(userEntity == null)
			throw  new CustomNullPointException("userEntity should not null");
		return signService.signin(userEntity);
	}

	@ApiOperation(value = "가입", notes = "회원가입을 한다.")
	@PostMapping(value = "/signup")
	public CommonResult signup(@RequestBody UserEntity userEntity) {
		if(userEntity == null)
			throw  new CustomNullPointException("userEntity should not null");
		return signService.signup(userEntity);
	}
}