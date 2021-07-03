package nk.demo.BlogAPIServer.Post;

import java.util.ArrayList;
import java.util.List;

import nk.demo.BlogAPIServer.Post.Dtos.BasicPostDto;
import nk.demo.BlogAPIServer.Post.Dtos.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nk.demo.BlogAPIServer.CustomException.PostValidationException;


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
	 * @param basicPostDto
	 * @return
	 * **/
	public int save(BasicPostDto basicPostDto) {
		if(basicPostDto.getContents() == null || basicPostDto.getTitle() == null || basicPostDto.getUserId() == 0)
			throw new PostValidationException("Not enough post data.");

		PostEntity postEntity = basicPostDto.toEntity();
		postRepository.save(postEntity);
		return postEntity.getPostId();
	}

	/**
	 * {postId}에 해당하는 게시글 수정
	 * @param basicPostDto
	 * @return
	 * **/
	public int update(int postId, BasicPostDto basicPostDto) {
		if(postId <= 0)
			throw new PostValidationException("postId cannot be minus.");

		if(basicPostDto.getContents() == null || basicPostDto.getTitle() == null || basicPostDto.getUserId() == 0)
			throw new PostValidationException("Not enough post data.");

		if(postRepository.get(postId) ==null)
			throw new PostValidationException("There is no corresponding information for postId.");

		PostEntity postEntity = basicPostDto.toEntity();
		postEntity.setPostId(postId);
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
