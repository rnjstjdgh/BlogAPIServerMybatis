package nk.demo.BlogAPIServer.Security.User;

import org.springframework.stereotype.Repository;

import nk.demo.BlogAPIServer.Post.PostEntity;

@Repository
public interface UserRepository {
	
	UserEntity getByEmail(String email);
	
	void save(UserEntity user);
}
