package com.agrumee.backend.service;
import com.agrumee.backend.dto.RegisterRequest;
import com.agrumee.backend.model.User;
import com.agrumee.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String email, String rawPassword) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    public User register(RegisterRequest request) {
        return register(request.getUsername(), request.getEmail(), request.getPassword());
    }

}
