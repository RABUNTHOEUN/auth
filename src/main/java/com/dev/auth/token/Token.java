package com.dev.auth.token;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Token {

  @Id
  @GeneratedValue
  private Integer id;

  private String token;

  private boolean revoked;

  private boolean expired;

}