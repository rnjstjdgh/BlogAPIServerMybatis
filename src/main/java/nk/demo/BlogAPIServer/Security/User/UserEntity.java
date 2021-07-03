package nk.demo.BlogAPIServer.Security.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder            // builder를 사용할수 있게 합니다.
@Getter             // user 필드값의 getter를 자동으로 생성합니다.
@Setter
@NoArgsConstructor  // 인자없는 생성자를 자동으로 생성합니다.
public class UserEntity{

    private int 				userId;
    private String 				email;
    private String 				password;
    private String 				role;

    @Builder
    public UserEntity(int userId, String email, String password, String role){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}