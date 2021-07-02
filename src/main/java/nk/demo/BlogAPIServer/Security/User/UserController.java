package nk.demo.BlogAPIServer.Security.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nk.demo.BlogAPIServer.Response.ListResult;
import nk.demo.BlogAPIServer.Response.ResponseService;

@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final ResponseService responseService; // 결과를 처리할 Service

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
//    @GetMapping(value = "/users")
//    public ListResult<UserEntity> findAllUser() {
//        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
//        return responseService.getListResult(userRepository.findAll());
//    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 단건 조회", notes = "회원번호(msrl)로 회원을 조회한다")
//    @GetMapping(value = "/user")
//    public SingleResult<User> findUserById(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
//        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String id = authentication.getName();
//        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
//        return responseService.getSingleResult(userJpaRepo.findByUid(id).orElseThrow(CUserNotFoundException::new));
//    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
//    @PutMapping(value = "/user")
//    public SingleResult<User> modify(
//            @ApiParam(value = "회원번호", required = true) @RequestParam int msrl,
//            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
//        User user = User.builder()
//                .msrl(msrl)
//                .name(name)
//                .build();
//        return responseService.getSingleResult(userJpaRepo.save(user));
//    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
//    @DeleteMapping(value = "/user/{msrl}")
//    public CommonResult delete(
//            @ApiParam(value = "회원번호", required = true) @PathVariable int msrl) {
//        userJpaRepo.deleteById(msrl);
//        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
//        return responseService.getSuccessResult();
//    }
}