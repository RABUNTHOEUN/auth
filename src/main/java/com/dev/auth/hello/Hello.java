package com.dev.auth.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
public class Hello {

  @GetMapping
  public ResponseEntity<String> hello() {
      return ResponseEntity.ok("Hello From API");
  }
  

}
