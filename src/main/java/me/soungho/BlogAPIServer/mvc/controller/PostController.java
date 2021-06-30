package me.soungho.BlogAPIServer.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.soungho.BlogAPIServer.CustomError.PostError;
import me.soungho.BlogAPIServer.mvc.domain.Post;
import me.soungho.BlogAPIServer.mvc.service.PostService;
import me.soungho.BlogAPIServer.CustomException.PostValidationException;
import org.springframework.http.HttpStatus;

/**
 * 게시판 컨트롤러
 * @author gshgsh0831
 * **/
@RestController
@RequestMapping("/posts")
@Api(tags = "게시판 API")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	/**
	 * 전체 게시글 리스트 리턴
	 * @return
	 * **/
	@GetMapping("")
	@ApiOperation(value = "게시글 목록 조회", 
	notes = "전체 게시글 리스트를 반환합니다.")
	public List<Post> getList(){
		return postService.getList();
	}
	
	/**
	 * {postSeq}에 해당하는 게시글 리턴, 없으면 null리턴
	 * @param postSeq
	 * @return
	 * **/
	@GetMapping("/{postSeq}")
	@ApiOperation(value = "게시글 상세 조회",
	notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postSeq",value = "게시물 번호", example = "1" )
	})
	public Post get(@PathVariable int postSeq) {
		return postService.get(postSeq);
	}
	
	/**
	 * 하나의 게시글 등록, 등록된 게시글의 postSeq리턴
	 * @param post
	 * @return
	 * **/
	@PostMapping("")
	@ApiOperation(value = "신규 게시글 등록", 
	notes = "신규 게시글을 등록합니다. 게시글 등록일 & 게시글 번호는 넘기지 않아야 합니다."
			+ "{title, user, contents}을 넘겨야 합니다.")
	public int save(@RequestBody Post post) {
		return postService.save(post);
	}
	
	/**
	 * {postSeq}에 해당하는 게시글 수정, 수정된 게시글의 postSeq리턴
	 * @param postSeq, post
	 * @return
	 * **/
	@PutMapping("/{postSeq}")
	@ApiOperation(value = "기존 게시글 수정", 
	notes = "기존 게시글을 수정합니다. 게시글 아이디는 path로 넘기고 나머지 정보를 json body로 넘겨야 합니다."
			+ "게시글 등록 일자는 넘기면 안됩니다.")
	public int update(@PathVariable int postSeq, @RequestBody Post post) {
		post.setPostSeq(postSeq);
		return postService.update(post);
	}
	
	/**
	 * {postSeq}에 해당하는 게시글 삭제, 삭제된 게시글의 postSeq리턴
	 * @param postSeq
	 * @return
	 * **/
	@DeleteMapping("/{postSeq}")
	@ApiOperation(value = "기존 게시글 삭제", 
	notes = "기존 게시글을 삭제합니다. path로 삭제하고자 하는 게시글의 번호를 넘겨야 합니다.")
	public int delete(@PathVariable int postSeq) {
		return postService.delete(postSeq);
	}
	
	@ExceptionHandler(PostValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	private PostError PostExceptionHandler(PostValidationException ex) {
		return new PostError(ex.getMessage());
	}
}
