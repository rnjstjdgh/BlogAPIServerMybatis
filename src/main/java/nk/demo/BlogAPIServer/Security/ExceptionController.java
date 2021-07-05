package nk.demo.BlogAPIServer.Security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import nk.demo.BlogAPIServer.CustomException.AuthenticationEntryPointException;
import nk.demo.BlogAPIServer.Response.CommonResult;

//import 생략

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/JWTException")
public class ExceptionController {

 @GetMapping(value = "/entrypoint")
 public CommonResult entrypointException() {
     throw new AuthenticationEntryPointException("Invalid JWT token!");
 }
}