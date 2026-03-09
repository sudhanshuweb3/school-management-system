package com.fees.management.service;

import com.fees.management.dto.LoginRequest;
import com.fees.management.dto.LoginResponse;
import com.fees.management.dto.RegisterRequest;
import com.fees.management.entity.Role;
import com.fees.management.entity.User;
import com.fees.management.repository.UserRepository;
import com.fees.management.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);

        userRepository.save(user);

        return "User registered successfully";
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        Long schoolId = user.getSchool() != null ? user.getSchool().getId() : null;
        String token = JwtUtil.generateToken(user.getEmail(), user.getRole().name(), schoolId);

        return new LoginResponse(token, user.getRole().name(), user.getName(), user.getEmail());
    }

}
