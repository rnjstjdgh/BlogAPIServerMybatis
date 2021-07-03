package nk.demo.BlogAPIServer.Security.Sign;

import lombok.RequiredArgsConstructor;
import nk.demo.BlogAPIServer.CustomException.SignFailedException;
import nk.demo.BlogAPIServer.Response.CommonResult;
import nk.demo.BlogAPIServer.Response.ResponseService;
import nk.demo.BlogAPIServer.Response.SingleResult;
import nk.demo.BlogAPIServer.Security.JWT.JwtTokenProvider;
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

    public SingleResult<String> signin(UserEntity userEntity) {
        UserEntity findedUserEntity = userRepository.getByEmail(userEntity.getEmail());
        if(findedUserEntity == null )
            throw new SignFailedException("login fail, check email");

        if (!passwordEncoder.matches(userEntity.getPassword(), findedUserEntity.getPassword()))
            throw new SignFailedException("login fail, check password");

        return responseService.getSingleResult(
                jwtTokenProvider.createToken(String.valueOf(findedUserEntity.getUserId()), findedUserEntity.getRole()));
    }

    public CommonResult signup(UserEntity userEntity) {
        if(userRepository.getByEmail(userEntity.getEmail()) != null)
            throw new SignFailedException("email overlap");

        userRepository.save(
                UserEntity.builder()
                        .email(userEntity.getEmail())
                        .password(passwordEncoder.encode(userEntity.getPassword()))
                        .role("ROLE_USER").build());
        return responseService.getSuccessResult();
    }
}
