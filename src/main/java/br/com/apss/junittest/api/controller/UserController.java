package br.com.apss.junittest.api.controller;

import br.com.apss.junittest.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

   @GetMapping("{id}")
   public ResponseEntity<User> findById(@PathVariable Long id){
      return ResponseEntity.ok(User.builder().id(1L).name("Adilson").email("add@yahoo.com.br").password("1254").build());
   }
}
