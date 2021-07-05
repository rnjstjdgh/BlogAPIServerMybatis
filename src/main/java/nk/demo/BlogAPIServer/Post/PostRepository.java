package nk.demo.BlogAPIServer.Post;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 게시판 레포지토리
 * @author gshgsh0831
 * **/
@Repository
public interface PostRepository {
	List<PostEntity> getList();
	
	PostEntity get(int postId);
	
	void save(PostEntity post);
	
	void update(PostEntity post);
	
	void delete(int postId);
}
