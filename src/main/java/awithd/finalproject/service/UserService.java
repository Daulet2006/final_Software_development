package awithd.finalproject.service;

import awithd.finalproject.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(UserDto dto);
}

