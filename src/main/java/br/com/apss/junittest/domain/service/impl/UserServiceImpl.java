package br.com.apss.junittest.domain.service.impl;

import br.com.apss.junittest.domain.model.User;
import br.com.apss.junittest.domain.repository.UserRepository;
import br.com.apss.junittest.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   private UserRepository userRepository;

   @Override
   public User findById(Long id) {
      Optional<User> user = userRepository.findById(id);
      return user.orElse(null);
   }
}
