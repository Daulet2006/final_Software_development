package awithd.finalproject.service;

import awithd.finalproject.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void login(String email,String password)
    void register(UserDto dto);
}

