package me.soungho.BlogAPIServer.Post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.soungho.BlogAPIServer.Response.ListResult;
import me.soungho.BlogAPIServer.Response.ResponseService;
import me.soungho.BlogAPIServer.Response.SingleResult;


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
	
	@Autowired
	private ResponseService responseService;
	
	/**
	 * 전체 게시글 리스트 리턴
	 * @return
	 * **/
	@GetMapping("")
	@ApiOperation(value = "게시글 목록 조회", 
	notes = "전체 게시글 리스트를 반환합니다.")
	public ListResult<PostDto> getList(){
		return responseService.getListResult(postService.getList());
	}
	
	/**
	 * {postId}에 해당하는 게시글 리턴, 없으면 null리턴
	 * @param postId
	 * @return
	 * **/
	@GetMapping("/{postId}")
	@ApiOperation(value = "게시글 상세 조회",
	notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postId",value = "게시물 번호", example = "1" )
	})
	public SingleResult<PostDto> get(@PathVariable int postId) {
		return responseService.getSingleResult(postService.get(postId));
	}
	
	/**
	 * 하나의 게시글 등록, 등록된 게시글의 postId리턴
	 * @param post
	 * @return
	 * **/
	@PostMapping("")
	@ApiOperation(value = "신규 게시글 등록", 
	notes = "신규 게시글을 등록합니다. 게시글 등록일 & 게시글 번호는 넘기지 않아야 합니다."
			+ "{title, userId, contents}을 넘겨야 합니다.")
	public SingleResult<Integer> save(@RequestBody PostDto postDto) {
		return responseService.getSingleResult(postService.save(postDto));
	}
	
	/**
	 * {postId}에 해당하는 게시글 수정, 수정된 게시글의 postId리턴
	 * @param postId, post
	 * @return
	 * **/
	@PutMapping("/{postId}")
	@ApiOperation(value = "기존 게시글 수정", 
	notes = "기존 게시글을 수정합니다. 게시글 아이디는 path로 넘기고 나머지 정보를 json body로 넘겨야 합니다."
			+ "게시글 등록 일자는 넘기면 안됩니다.")
	public SingleResult<Integer> update(@PathVariable int postId, @RequestBody PostDto postDto) {
		postDto.setPostId(postId);
		return responseService.getSingleResult(postService.update(postDto));
	}
	
	/**
	 * {postId}에 해당하는 게시글 삭제, 삭제된 게시글의 postId리턴
	 * @param postId
	 * @return
	 * **/
	@DeleteMapping("/{postId}")
	@ApiOperation(value = "기존 게시글 삭제", 
	notes = "기존 게시글을 삭제합니다. path로 삭제하고자 하는 게시글의 번호를 넘겨야 합니다.")
	public SingleResult<Integer> delete(@PathVariable int postId) {
		return responseService.getSingleResult(postService.delete(postId));
	}
}
