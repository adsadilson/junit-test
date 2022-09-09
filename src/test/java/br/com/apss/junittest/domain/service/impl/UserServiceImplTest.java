package br.com.apss.junittest.domain.service.impl;

import br.com.apss.junittest.api.dto.UserDTO;
import br.com.apss.junittest.api.exceptions.DataIntegrityViolationException;
import br.com.apss.junittest.api.exceptions.EntityNotFoundException;
import br.com.apss.junittest.api.exceptions.RecordInUseException;
import br.com.apss.junittest.domain.entity.User;
import br.com.apss.junittest.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

   public static final long ID = 1L;
   public static final String MSN_RECORD_IN_USER = String.format("%s of code %d cannot be deleted as it has links to other tables", User.class, ID);
   public static final String NAME = "Fulano";
   public static final String EMAIL = "fulano@yahoo.com.br";
   public static final String PASSWORD = "5415";
   public static final String OBJECT_NOT_FOUND = "Object not found.";
   public static final int INDEX = 0;
   public static final String MAIL_ALREADY_EXISTS = "E-mail already exists!";


   @InjectMocks
   private UserServiceImpl service;

   @Mock
   private UserRepository repository;

   @Mock
   private ModelMapper mapper;
   private User user;
   private UserDTO userDTO;

   private Optional<User> optionalUser;


   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
      startUP();
   }

   @Test
   void whenFindByIdThenReturnAnUserInstance() {
      when(repository.findById(anyLong())).thenReturn(optionalUser);

      User response = service.findById(ID);

      assertNotNull(response);
      assertEquals(User.class, response.getClass());
      assertEquals(ID, response.getId());
      assertEquals(NAME, response.getName());
      assertEquals(EMAIL, response.getEmail());
   }

   @Test
   void whenFindByIdThenReturnEntityNotFoudException() {
      when(repository.findById(anyLong())).thenThrow(new EntityNotFoundException(OBJECT_NOT_FOUND));
      try {
         service.findById(ID);
      } catch (Exception ex) {
         assertEquals(EntityNotFoundException.class, ex.getClass());
         assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
      }
   }

   @Test
   void whenFindAllThenReturnAnListOfUsers() {
      when(repository.findAll()).thenReturn(List.of(user));
      List<User> response = service.findAll();
      assertNotNull(response);
      assertEquals(1, response.size());

      assertEquals(User.class, response.get(INDEX).getClass());
      assertEquals(ID, response.get(INDEX).getId());
      assertEquals(NAME, response.get(INDEX).getName());
      assertEquals(EMAIL, response.get(INDEX).getEmail());
   }

   @Test
   void whenCreateThenSuccess() {
      when(repository.save(any())).thenReturn(user);
      User response = service.create(userDTO);
      assertNotNull(response);
      assertEquals(User.class, response.getClass());
   }

   @Test
   void whenCreateThenDataInegratyViolationException() {
      when(repository.findByEmail(anyString())).thenReturn(optionalUser);
      try {
         optionalUser.get().setId(2l);
         service.create(userDTO);
      } catch (DataIntegrityViolationException ex) {
         assertEquals(DataIntegrityViolationException.class, ex.getClass());
         assertEquals(MAIL_ALREADY_EXISTS, ex.getMessage());
      }
   }

   @Test
   void whenUpdateThenSuccess() {
      when(repository.save(any())).thenReturn(user);
      User response = service.update(userDTO);
      assertNotNull(response);
      assertEquals(User.class, response.getClass());
   }

   @Test
   void whenUpdateThenDataInegratyViolationException() {
      when(repository.findByEmail(anyString())).thenThrow(new DataIntegrityViolationException(MAIL_ALREADY_EXISTS));
      try {
         service.create(userDTO);
      } catch (DataIntegrityViolationException ex) {
         assertEquals(DataIntegrityViolationException.class, ex.getClass());
         assertEquals(MAIL_ALREADY_EXISTS, ex.getMessage());
      }
   }

   @Test
   void whenDeleteThenSuccess() {
      doNothing().when(repository).deleteById(anyLong());
      service.delete(ID);
      verify(repository, times(1)).deleteById(anyLong());
   }

   @Test
   void whenDeleteThenEntityNotFoundException() {
      doThrow(new EntityNotFoundException(OBJECT_NOT_FOUND)).when(repository).deleteById(anyLong());
      try {
         service.delete(ID);
      } catch (Exception ex) {
         assertEquals(EntityNotFoundException.class, ex.getClass());
         assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
      }
   }

   @Test
   void whenDeleteThenRecordInUseException() {
      doThrow(new RecordInUseException(MSN_RECORD_IN_USER)).when(repository).deleteById(anyLong());
      try {
         service.delete(ID);
      } catch (Exception ex) {
         assertEquals(RecordInUseException.class, ex.getClass());
         assertEquals(MSN_RECORD_IN_USER, ex.getMessage());
      }
   }

   void startUP() {
      user = new User(ID, NAME, EMAIL, PASSWORD);
      userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
      optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
   }
}