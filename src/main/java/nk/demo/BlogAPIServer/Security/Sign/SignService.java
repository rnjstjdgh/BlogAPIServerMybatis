package nk.demo.BlogAPIServer.Security.Sign;

import lombok.RequiredArgsConstructor;
import nk.demo.BlogAPIServer.CustomException.SignFailedException;
import nk.demo.BlogAPIServer.Response.CommonResult;
import nk.demo.BlogAPIServer.Response.ResponseService;
import nk.demo.BlogAPIServer.Response.SingleResult;
import nk.demo.BlogAPIServer.Security.JWT.JwtTokenProvider;
import nk.demo.BlogAPIServer.Security.User.UserDto;
import nk.demo.BlogAPIServer.Security.User.UserEntity;
import nk.demo.BlogAPIServer.Security.User.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    public SingleResult<String> signin(String email, String password) {
        UserEntity findedUserEntity = userRepository.getByEmail(email);
        if(findedUserEntity == null )
            throw new SignFailedException("login fail, check email");

        if (!passwordEncoder.matches(password, findedUserEntity.getPassword()))
            throw new SignFailedException("login fail, check password");

        return responseService.getSingleResult(
                jwtTokenProvider.createToken(String.valueOf(findedUserEntity.getUserId()), findedUserEntity.getRole()));
    }

    public CommonResult signup(String email, String password) {
        if(userRepository.getByEmail(email) != null)
            throw new SignFailedException("email overlap");
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role("ROLE_USER").build();
        userRepository.save(userEntity);
        return responseService.getSingleResult(userEntity.getUserId());
    }
}
