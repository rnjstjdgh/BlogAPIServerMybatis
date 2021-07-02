package me.soungho.BlogAPIServer.CustomException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;
import me.soungho.BlogAPIServer.Response.CommonResult;
import me.soungho.BlogAPIServer.Response.ResponseService;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "me.soungho.BlogAPIServer")
public class ExceptionAdvice {

    private final ResponseService responseService;

	@ExceptionHandler(PostValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult PostExceptionHandler(Exception ex) {
		return responseService.getFailResult(ex.getMessage());
    }
}