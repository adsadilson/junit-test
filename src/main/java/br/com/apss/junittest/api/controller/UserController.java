package br.com.apss.junittest.api.controller;

import br.com.apss.junittest.api.dto.UserDTO;
import br.com.apss.junittest.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

   public static final String ID = "/{id}";
   private ModelMapper mapper;

   private UserService service;

   @GetMapping(ID)
   public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
      val user = service.findById(id);
      return ResponseEntity.ok(mapper.map(user, UserDTO.class));
   }

   @GetMapping
   public List<UserDTO> findAll() {
      return service.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
      val userSave = service.create(userDTO);
      return ResponseEntity.ok(mapper.map(userSave, UserDTO.class));
   }

   @PutMapping(ID)
   public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
      userDTO.setId(id);
      return ResponseEntity.ok(mapper.map(service.update(userDTO), UserDTO.class));
   }

   @DeleteMapping(ID)
   public ResponseEntity<?> delete(@PathVariable Long id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }
}
