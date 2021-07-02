package nk.demo.BlogAPIServer.Post;

import java.util.List;

import org.springframework.stereotype.Repository;

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
	List<PostEntity> getList();
	
	/**
	 * {postId}에 해당하는 게시글 리턴
	 * @param postId
	 * @return
	 * **/
	PostEntity get(int postId);
	
	
	/**
	 * 하나의 게시글 등록
	 * @param post
	 * **/
	void save(PostEntity post);
	
	
	/**
	 * {postId}에 해당하는 게시글 수정
	 * @param postId, post
	 * **/
	void update(PostEntity post);
	
	/**
	 * {postId}에 해당하는 게시글 삭제
	 * @param postId
	 * **/
	void delete(int postId);
}
