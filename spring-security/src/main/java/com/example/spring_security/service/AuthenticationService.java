package com.example.spring_security.service;/*
 * @author -Suraj Tiwari
 */

import com.example.spring_security.dto.AuthenticateRequest;
import com.example.spring_security.dto.AuthenticationResponse;
import com.example.spring_security.dto.RegisterRequest;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.user.Role;
import com.example.spring_security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthService jwtAuthService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
   var user= User.builder()
           .firstName(registerRequest.getFirstName())
           .lastName(registerRequest.getLastName())
           .email(registerRequest.getEmail())
           .password(passwordEncoder.encode(registerRequest.getPassword()))
           .role(Role.USER)
           .build();

   userRepository.save(user);

   String token=jwtAuthService.generateToken(user);

   return   AuthenticationResponse.builder().token(token).build();



    }

    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(),authenticateRequest.getPassword()));
      var user=userRepository.findByEmail(authenticateRequest.getEmail()).orElseThrow();
        String token=jwtAuthService.generateToken(user);

        return   AuthenticationResponse.builder().token(token).build();
    }
}
