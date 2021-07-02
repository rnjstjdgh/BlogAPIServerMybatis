package nk.demo.BlogAPIServer.Security.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) {
        return userRepository.getByEmail(email);
    }
}