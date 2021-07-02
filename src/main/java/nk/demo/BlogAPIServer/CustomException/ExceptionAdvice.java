package nk.demo.BlogAPIServer.CustomException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;
import nk.demo.BlogAPIServer.Response.CommonResult;
import nk.demo.BlogAPIServer.Response.ResponseService;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "nk.demo.BlogAPIServer")
public class ExceptionAdvice {

    private final ResponseService responseService;

	@ExceptionHandler(PostValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult PostExceptionHandler(Exception ex) {
		return responseService.getFailResult(ex.getMessage());
    }
	
	@ExceptionHandler(EmailSigninFailedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	protected CommonResult emailSigninFailed(HttpServletRequest request, EmailSigninFailedException ex) {
	    return responseService.getFailResult(ex.getMessage());
	}
}