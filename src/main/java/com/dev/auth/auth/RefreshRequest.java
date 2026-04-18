package com.dev.auth.auth;

import lombok.Data;

@Data
public class RefreshRequest {
  private String refreshToken;
}