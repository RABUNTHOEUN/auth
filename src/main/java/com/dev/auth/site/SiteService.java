package com.dev.auth.site;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {

  private final SiteRepository repo;

  public Site save(String name, String config) {

    Site site = Site.builder()
        .name(name)
        .config(config)
        .build();

    return repo.save(site);
  }

  public List<Site> findAll() {
    return repo.findAll();
  }

  public Site find(Long id) {
    return repo.findById(id).orElseThrow();
  }

  public Site update(Long id, String name, String config) {
    Site site = repo.findById(id).orElseThrow();

    site.setName(name);
    site.setConfig(config);

    return repo.save(site);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
}