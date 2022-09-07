package br.com.apss.junittest.domain.service.impl;

import br.com.apss.junittest.api.dto.UserDTO;
import br.com.apss.junittest.api.exceptions.DataIntegrityViolationException;
import br.com.apss.junittest.api.exceptions.EntityNotFoundException;
import br.com.apss.junittest.api.exceptions.RecordInUseException;
import br.com.apss.junittest.domain.entity.User;
import br.com.apss.junittest.domain.repository.UserRepository;
import br.com.apss.junittest.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   private static final String NAMECLASS = "User";
   private UserRepository repository;
   private ModelMapper mapper;

   @Override
   public User findById(Long id) {
      Optional<User> user = repository.findById(id);
      return user.orElseThrow(() -> new EntityNotFoundException("Object not found."));
   }

   @Override
   public List<User> findAll() {
      return repository.findAll();
   }

   @Override
   public User create(UserDTO userDTO) {
      existsEmail(userDTO);
      return repository.save(mapper.map(userDTO, User.class));
   }

   @Override
   public User update(UserDTO userDTO) {
      return create(userDTO);
   }

   @Override
   @Transactional
   public void delete(Long id) {
      try {
         repository.deleteById(id);
         repository.flush();
      } catch (EmptyResultDataAccessException e) {
         throw new EntityNotFoundException(NAMECLASS, id);
      } catch (DataIntegrityViolationException e) {
         throw new RecordInUseException(NAMECLASS, id);
      }
   }

   private void existsEmail(UserDTO userDTO) {
      boolean result = repository.findByEmail(userDTO.getEmail()).stream().anyMatch(user -> !user.getId().equals(userDTO.getId()));
      if (result) {
         throw new DataIntegrityViolationException("E-mail already exists!");
      }
   }

}
