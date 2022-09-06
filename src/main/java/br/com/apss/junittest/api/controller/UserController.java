package br.com.apss.junittest.api.controller;

import br.com.apss.junittest.api.dto.UserDTO;
import br.com.apss.junittest.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

   private ModelMapper mapper;

   private UserService service;
   @GetMapping("/{id}")
   public ResponseEntity<UserDTO> findById(@PathVariable Long id){
      val user = service.findById(id);
      return ResponseEntity.ok(mapper.map(user, UserDTO.class));
   }
   @GetMapping
   public List<UserDTO> findAll(){
      return service.findAll().stream().map( user->mapper.map(user,UserDTO.class)).collect(Collectors.toList());
   }
}
