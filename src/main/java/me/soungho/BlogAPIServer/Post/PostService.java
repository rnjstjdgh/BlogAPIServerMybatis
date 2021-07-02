package me.soungho.BlogAPIServer.Post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.soungho.BlogAPIServer.CustomException.PostValidationException;


/**
 * 게시판 서비스
 * @author gshgsh0831
 * **/
@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	/**
	 * 전체 게시글 리스트 리턴
	 * @return
	 * **/
	public List<PostDto> getList(){
		List<PostEntity> postEntityList = postRepository.getList();
		List<PostDto> postDtoList = new ArrayList<>();
		for(PostEntity postEntity : postEntityList)
			postDtoList.add(this.convertEntityToDto(postEntity));
        
		return postDtoList;
	}
	
	/**
	 * {postId}에 해당하는 게시글 리턴
	 * @param postId
	 * @return
	 * **/
	public PostDto get(int postId) {
		if(postId < 0)
			throw new PostValidationException("postId cannot be minus.");
		PostEntity postEntity = postRepository.get(postId);
		if(postEntity == null)
			throw new PostValidationException("There is no corresponding information for postId.");
		return PostDto.builder()
				.postId(postEntity.getPostId())
				.title(postEntity.getTitle())
				.userId(postEntity.getUserId())
				.contents(postEntity.getContents())
				.regDate(postEntity.getRegDate())
				.build();
	}
	
	/**
	 * 하나의 게시글 등록
	 * @param post
	 * @return
	 * **/
	public int save(PostDto postDto) {
		if(postDto.getContents() == null || postDto.getTitle() == null || postDto.getUserId() == 0)
			throw new PostValidationException("Not enough post data.");
		if(postDto.getPostId() != 0) 
			throw new PostValidationException("should not send postId.");
		if(postDto.getRegDate() != null)
			throw new PostValidationException("should not send regDate.");
		PostEntity postEntity = postDto.toEntity();
		postRepository.save(postEntity);
		return postEntity.getPostId();
	}
	
	
	/**
	 * {postId}에 해당하는 게시글 수정
	 * @param postId, post
	 * @return
	 * **/
	public int update(PostDto postDto) {
		if(postDto.getPostId() <= 0) 
			throw new PostValidationException("postId cannot be minus.");
		if(postDto.getRegDate() !=null)
			throw new PostValidationException("don't need regDate.");
		if(postRepository.get(postDto.getPostId()) ==null)
			throw new PostValidationException("There is no corresponding information for postId.");
		PostEntity postEntity = postDto.toEntity();
		postRepository.update(postEntity);		
		return postEntity.getPostId();
	}
	
	/**
	 * {postId}에 해당하는 게시글 삭제
	 * @param postId
	 * @return
	 * **/
	public int delete(int postId) {
		if(postId <= 0) 
			throw new PostValidationException("postId cannot be minus.");
		if(postRepository.get(postId) ==null)
			throw new PostValidationException("There is no corresponding information for postId.");
		postRepository.delete(postId);
		return postId;
	}
	
    private PostDto convertEntityToDto(PostEntity postEntity){
        return PostDto.builder()
        				.postId(postEntity.getPostId())
        				.title(postEntity.getTitle())
        				.userId(postEntity.getUserId())
        				.contents(postEntity.getContents())
        				.regDate(postEntity.getRegDate())
        				.build();
    }
}
