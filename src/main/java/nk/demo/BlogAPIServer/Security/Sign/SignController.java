package nk.demo.BlogAPIServer.Security.Sign;

import nk.demo.BlogAPIServer.CustomException.CustomNullPointException;
import nk.demo.BlogAPIServer.Security.User.UserDto;
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
	private final SignService signService;

	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
	@PostMapping(value = "/signin")
	public SingleResult<String> signin(@RequestBody UserDto userDto) {
		if(userDto == null)
			throw  new CustomNullPointException("userDto should not null");
		return signService.signin(userDto);
	}

	@ApiOperation(value = "가입", notes = "회원가입을 한다.")
	@PostMapping(value = "/signup")
	public CommonResult signup(@RequestBody UserDto userDto) {
		if(userDto == null)
			throw  new CustomNullPointException("userEntity should not null");
		return signService.signup(userDto);
	}
}