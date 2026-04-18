package com.dev.auth.token;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

  private final RefreshTokenRepository repository;

  private static final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7; // 7 days

  public RefreshToken createRefreshToken(String email) {

    repository.deleteByEmail(email);

    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setEmail(email);
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION));

    return repository.save(refreshToken);
  }

  public RefreshToken verifyToken(String token) {

    RefreshToken refreshToken = repository.findByToken(token)
        .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

    if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
      repository.delete(refreshToken);
      throw new RuntimeException("Refresh token expired");
    }

    return refreshToken;
  }
}