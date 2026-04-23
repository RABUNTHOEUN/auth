package com.dev.auth.site;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SiteResponse {

  private Long id;
  private String name;
  private String config;
}