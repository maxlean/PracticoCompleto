package com.AlkemyJava2TP3.PracticoParte3.service;

import com.AlkemyJava2TP3.PracticoParte3.Enums.Roles;
import com.AlkemyJava2TP3.PracticoParte3.model.AuthResponse;
import com.AlkemyJava2TP3.PracticoParte3.model.LoginRequest;
import com.AlkemyJava2TP3.PracticoParte3.model.RegisterRequest;
import com.AlkemyJava2TP3.PracticoParte3.model.User;

import com.AlkemyJava2TP3.PracticoParte3.repository.UserRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Tag("PruebasUnitariasAuthService")
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private User user;
    @InjectMocks
    private ProductoService productoService;
    @InjectMocks
    private AuthService authService;

    @Test
    void login_deberiaRetornarAuthResponseSiCredencialesSonCorrectas() {
        LoginRequest request = new LoginRequest("usuario", "password");
        User user = User.builder()
                .username("usuario")
                .password(passwordEncoder.encode("password"))
                .build();
        when(userRepository.findByUsername("usuario")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", user.getPassword())).thenReturn(true);
        when(jwtService.getToken(user)).thenReturn("token");

        AuthResponse response = authService.login(request);

        assertEquals("token", response.getToken());
    }

    @Test
    void login_deberiaLanzarExcepcionSiUsuarioNoExiste() {
        LoginRequest request = new LoginRequest("usuario", "password");
        when(userRepository.findByUsername("usuario")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.login(request));
    }

    @Test
    void login_deberiaLanzarExcepcionSiPasswordEsIncorrecto() {
        LoginRequest request = new LoginRequest("usuario", "password");
        User user = User.builder()
                .username("usuario")
                .password(passwordEncoder.encode("otraPassword"))
                .build();
        when(userRepository.findByUsername("usuario")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", user.getPassword())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.login(request));
    }

    @Test
    void register_deberiaGuardarUsuarioYRetornarAuthResponse() {
        RegisterRequest request = new RegisterRequest("usuario", "password", "email@test.com", "Nombre", "Apellido");
        User user = User.builder()
                .username("usuario")
                .password(passwordEncoder.encode("password"))
                .email("email@test.com")
                .firstName("Nombre")
                .lastName("Apellido")
                .rol(Roles.USER)
                .build();
        when(jwtService.getToken(any(User.class))).thenReturn("token");

        AuthResponse response = authService.register(request);

        verify(userRepository, times(1)).save(any(User.class));
        assertEquals("token", response.getToken());
    }
}
