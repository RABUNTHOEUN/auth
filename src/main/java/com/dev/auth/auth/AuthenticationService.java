package com.dev.auth.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.auth.User.Role;
import com.dev.auth.User.User;
import com.dev.auth.User.UserRepository;
import com.dev.auth.config.JwtService;
import com.dev.auth.token.RefreshToken;
import com.dev.auth.token.RefreshTokenService;
import com.dev.auth.token.Token;
import com.dev.auth.token.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final TokenRepository tokenRepository;
  private final RefreshTokenService refreshTokenService;

  private void saveUserToken(String jwtToken) {
    Token token = new Token();
    token.setToken(jwtToken);
    token.setExpired(false);
    token.setRevoked(false);
    tokenRepository.save(token);
  }

  public AuthenticationResponse register(RegisterRequest request) {

    if (repository.findByEmail(request.getEmail()).isPresent()) {
      throw new RuntimeException("Email already registered");
    }

    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    repository.save(user);

    var accessToken = jwtService.generateToken(user);
    saveUserToken(accessToken);

    var refreshToken = refreshTokenService.createRefreshToken(user.getEmail());

    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken.getToken())
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getEmail(),
              request.getPassword()));
    } catch (Exception e) {
      System.out.println("AUTH FAILED: " + e.getMessage());
      throw e;
    }

    var user = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    var accessToken = jwtService.generateToken(user);
    saveUserToken(accessToken);

    var refreshToken = refreshTokenService.createRefreshToken(user.getEmail());

    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken.getToken())
        .build();
  }

  public AuthenticationResponse refreshToken(RefreshRequest request) {

    RefreshToken refreshToken = refreshTokenService.verifyToken(request.getRefreshToken());

    var user = repository.findByEmail(refreshToken.getEmail())
        .orElseThrow();

    var newAccessToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
        .accessToken(newAccessToken)
        .refreshToken(refreshToken.getToken())
        .build();
  }

  public void logout(HttpServletRequest request) {

    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }

    String jwt = authHeader.substring(7);

    var storedToken = tokenRepository.findByToken(jwt);

    storedToken.ifPresent(token -> {
      token.setExpired(true);
      token.setRevoked(true);
      tokenRepository.save(token);
    });
  }
}