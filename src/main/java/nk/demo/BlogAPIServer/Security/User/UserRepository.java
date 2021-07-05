package nk.demo.BlogAPIServer.Security.User;

import java.util.List;

import org.springframework.stereotype.Repository;

import nk.demo.BlogAPIServer.Post.PostEntity;

/**
 * 게시판 레포지토리
 * @author gshgsh0831
 * **/
@Repository
public interface UserRepository {
	
	List<UserEntity> getList();
	
	UserEntity get(int userId);
	
	void save(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(int userId);
	
	UserEntity getByEmail(String email);
	
}
