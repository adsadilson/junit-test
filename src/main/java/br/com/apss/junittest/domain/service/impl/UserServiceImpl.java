package br.com.apss.junittest.domain.service.impl;

import br.com.apss.junittest.api.exceptions.ObjectNotFoundException;
import br.com.apss.junittest.domain.entity.User;
import br.com.apss.junittest.domain.repository.UserRepository;
import br.com.apss.junittest.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   private UserRepository repository;

   @Override
   public User findById(Long id) {
      Optional<User> user = repository.findById(id);
      return user.orElseThrow(()->new ObjectNotFoundException("Object not found."));
   }

   @Override
   public List<User> findAll() {
      return repository.findAll();
   }
}
