package nk.demo.BlogAPIServer.Security.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nk.demo.BlogAPIServer.CustomException.ApiValidationException;
import nk.demo.BlogAPIServer.Security.User.Dtos.BasicUserDto;
import nk.demo.BlogAPIServer.Security.User.Dtos.UserDto;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	/**
	 * 전체 사용자 리스트 리턴
	 * @return
	 * **/
	public List<UserDto> getList(){
		List<UserEntity> userEntityList = userRepository.getList();
		List<UserDto> userDtoList = new ArrayList<>();
		for(UserEntity userEntity : userEntityList)
			userDtoList.add(this.convertEntityToDto(userEntity));
        
		return userDtoList;
	}
	
	/**
	 * {userId}에 해당하는 사용자 리턴
	 * @param userId
	 * @return
	 * **/
	public UserDto get(int userId) {
		if(userId < 0)
			throw new ApiValidationException("userId cannot be minus.");
		UserEntity userEntity = userRepository.get(userId);
		if(userEntity == null)
			throw new ApiValidationException("There is no corresponding information for userId.");
		return convertEntityToDto(userEntity);
	}
	
	/**
	 * 하나의 사용자 등록
	 * @param userDto
	 * @return
	 * **/
	public int save(BasicUserDto userDto) {
		if(userDto.getEmail() == null || userDto.getPassword() == null || userDto.getRole() == null)
			throw new ApiValidationException("Not enough user data.");

		UserEntity userEntity = userDto.toEntity();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
		return userEntity.getUserId();
	}
	
	/**
	 * {userId}에 해당하는 사용자 수정
	 * @param userDto
	 * @return
	 * **/
	public int update(int userId, BasicUserDto userDto) {
		if(userId <= 0)
			throw new ApiValidationException("userId cannot be minus.");

		if(userDto.getEmail() == null || userDto.getPassword() == null || userDto.getRole() == null)
			throw new ApiValidationException("Not enough user data.");

		if(userRepository.get(userId) ==null)
			throw new ApiValidationException("There is no corresponding information for userId.");

		UserEntity userEntity = userDto.toEntity();
		userEntity.setUserId(userId);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.update(userEntity);		
		return userEntity.getUserId();
	}
	
	
	/**
	 * {userId}에 해당하는 사용자 삭제
	 * @param userId
	 * @return
	 * **/
	public int delete(int userId) {
		if(userId <= 0) 
			throw new ApiValidationException("userId cannot be minus.");
		if(userRepository.get(userId) ==null)
			throw new ApiValidationException("There is no corresponding information for userId.");
		userRepository.delete(userId);
		return userId;
	}
	
    public UserDetails loadUserByUsername(String email) {
        UserEntity userEntity = userRepository.getByEmail(email);
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .regDate(userEntity.getRegDate())
                .role(userEntity.getRole())
                .build();
    }
    
    
    private UserDto convertEntityToDto(UserEntity userEntity){
        return UserDto.builder()
        				.userId(userEntity.getUserId())
        				.email(userEntity.getEmail())
        				.password(userEntity.getPassword())
        				.role(userEntity.getRole())
        				.regDate(userEntity.getRegDate())
        				.build();
    }
}