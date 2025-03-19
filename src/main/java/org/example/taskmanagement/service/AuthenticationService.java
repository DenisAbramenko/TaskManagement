package org.example.taskmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagement.dto.JwtAuthenticationResponse;
import org.example.taskmanagement.dto.UserDTO;
import org.example.taskmanagement.entity.Role;
import org.example.taskmanagement.entity.Token;
import org.example.taskmanagement.entity.User;
import org.example.taskmanagement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(UserDTO request) {

        User.UserBuilder builder = User.builder();
        builder.email(request.getEmail());
        builder.password(passwordEncoder.encode(request.getPassword()));
        builder.role(Role.ROLE_USER);
        User user = builder.build();

        userService.create(user);

        String jwt = jwtService.generateToken(user);
        Token.builder().user(user).token(jwt).loggedOut(false);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(UserDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));

        UserDetails user = userService.userDetailsService().loadUserByUsername(request.getEmail());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Удаление пользователя из системы
     */
    public void logout(String token) {
        Token.builder().loggedOut(false).token(token).user(userService.getCurrentUser()).build();
        userRepository.delete(userService.getCurrentUser());
    }
}
