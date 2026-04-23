package com.dev.auth.site;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {

 private final SiteRepository repo;

 public Site save(String name,String config){

   Site site = Site.builder()
           .name(name)
           .config(config)
           .build();

   return repo.save(site);
 }

 public List<Site> findAll(){
   return repo.findAll();
 }

 public Site find(Long id){
   return repo.findById(id).orElseThrow();
 }
}