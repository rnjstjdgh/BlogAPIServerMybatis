package me.soungho.BlogAPIServer.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import me.soungho.BlogAPIServer.CustomException.PostValidationException;
import me.soungho.BlogAPIServer.mvc.domain.Post;
import me.soungho.BlogAPIServer.mvc.repository.PostRepository;


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
	public List<Post> getList(){
		return postRepository.getList();
	}
	
	/**
	 * {postSeq}에 해당하는 게시글 리턴
	 * @param postSeq
	 * @return
	 * **/
	public Post get(int postSeq) {
		if(postSeq < 0)
			throw new PostValidationException("postSeq cannot be minus.");
		Post post = postRepository.get(postSeq);
		if(post == null)
			throw new PostValidationException("There is no corresponding information for postSeq.");
		return post;
	}
	
	/**
	 * 하나의 게시글 등록
	 * @param post
	 * @return
	 * **/
	public int save(Post post) {
		if(post.getContents() == null || post.getTitle() == null || post.getUser() == null)
			throw new PostValidationException("Not enough post data.");
		if(post.getPostSeq() != 0) 
			throw new PostValidationException("should not send postSeq.");
		if(post.getRegDate() != null)
			throw new PostValidationException("should not send regDate.");
		postRepository.save(post);
		return post.getPostSeq();
	}
	
	
	/**
	 * {postSeq}에 해당하는 게시글 수정
	 * @param postSeq, post
	 * @return
	 * **/
	public int update(Post post) {
		if(post.getPostSeq() <= 0) 
			throw new PostValidationException("postSeq cannot be minus.");
		if(post.getRegDate() !=null)
			throw new PostValidationException("don't need regDate.");
		if(postRepository.get(post.getPostSeq()) ==null)
			throw new PostValidationException("There is no corresponding information for postSeq.");
		
		postRepository.update(post);		
		return post.getPostSeq();
	}
	
	/**
	 * {postSeq}에 해당하는 게시글 삭제
	 * @param postSeq
	 * @return
	 * **/
	public int delete(int postSeq) {
		if(postSeq <= 0) 
			throw new PostValidationException("postSeq cannot be minus.");
		if(postRepository.get(postSeq) ==null)
			throw new PostValidationException("There is no corresponding information for postSeq.");
		postRepository.delete(postSeq);
		return postSeq;
	}
}
