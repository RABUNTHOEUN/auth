package com.dev.auth.site;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
@CrossOrigin
public class SiteController {

 private final SiteService service;

 @PostMapping
 public Site save(@RequestBody Site request){

   return service.save(request.getName(),request.getConfig());
 }

 @GetMapping
 public List<Site> list(){
   return service.findAll();
 }

 @GetMapping("/{id}")
 public Site get(@PathVariable Long id){
   return service.find(id);
 }
}
