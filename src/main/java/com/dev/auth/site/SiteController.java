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

 @PutMapping("/{id}")
  public Site update(@PathVariable Long id, @RequestBody SiteDto dto) {
    return service.update(id, dto.getName(), dto.getConfig());
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
