package awithd.finalproject.service.impl;

import awithd.finalproject.dto.UserDto;
import awithd.finalproject.entity.Permission;
import awithd.finalproject.entity.User;
import awithd.finalproject.mapper.UserMapper;
import awithd.finalproject.repository.PermissionRepository;
import awithd.finalproject.repository.UserRepository;
import awithd.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email)
                );
    }

    @Override
    public void register(UserDto dto) {

        if (userRepository.existsByEmail(dto.getEmailDto())) {
            throw new RuntimeException("User already exists");
        }

        User user = userMapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.getPasswordDto()));

        Permission userPermission = permissionRepository
                .findByName("ROLE_USER")
                .orElseThrow(() ->
                        new RuntimeException("Permission ROLE_USER not found")
                );

        Set<Permission> permissions = new HashSet<>();
        permissions.add(userPermission);

        user.setPermissions(permissions);

        userRepository.save(user);
    }
}
