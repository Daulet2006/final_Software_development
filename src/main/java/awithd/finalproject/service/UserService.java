package awithd.finalproject.service;

import awithd.finalproject.dto.UserDto;
import awithd.finalproject.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(UserDto dto);
}

