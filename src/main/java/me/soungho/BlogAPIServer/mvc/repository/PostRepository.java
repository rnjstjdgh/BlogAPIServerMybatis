package me.soungho.BlogAPIServer.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import me.soungho.BlogAPIServer.mvc.domain.Post;

/**
 * 게시판 레포지토리
 * @author gshgsh0831
 * **/
@Repository
public interface PostRepository {
	
	/**
	 * 전체 게시글 리스트 리턴
	 * @return
	 * **/
	List<Post> getList();
	
	/**
	 * {postSeq}에 해당하는 게시글 리턴
	 * @param postSeq
	 * @return
	 * **/
	Post get(int postSeq);
	
	
	/**
	 * 하나의 게시글 등록
	 * @param post
	 * **/
	void save(Post post);
	
	
	/**
	 * {postSeq}에 해당하는 게시글 수정
	 * @param postSeq, post
	 * **/
	void update(Post post);
	
	/**
	 * {postSeq}에 해당하는 게시글 삭제
	 * @param postSeq
	 * **/
	void delete(int postSeq);
}