package com.AlkemyJava2TP3.PracticoParte3.service;

import com.AlkemyJava2TP3.PracticoParte3.Enums.Roles;
import com.AlkemyJava2TP3.PracticoParte3.model.AuthResponse;
import com.AlkemyJava2TP3.PracticoParte3.model.LoginRequest;
import com.AlkemyJava2TP3.PracticoParte3.model.RegisterRequest;
import com.AlkemyJava2TP3.PracticoParte3.model.User;
import com.AlkemyJava2TP3.PracticoParte3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUsername()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .rol(Roles.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
