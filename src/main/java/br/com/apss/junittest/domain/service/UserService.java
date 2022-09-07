package br.com.apss.junittest.domain.service;

import br.com.apss.junittest.api.dto.UserDTO;
import br.com.apss.junittest.domain.entity.User;

import java.util.List;

public interface UserService {
   User findById(Long id);

   List<User> findAll();

   User create(UserDTO userDTO);

   User update(UserDTO userDTO);

   void delete(Long id);
}
