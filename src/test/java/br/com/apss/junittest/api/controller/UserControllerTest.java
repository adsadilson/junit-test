package br.com.apss.junittest.api.controller;

import br.com.apss.junittest.api.dto.UserDTO;
import br.com.apss.junittest.domain.entity.User;
import br.com.apss.junittest.domain.service.impl.UserServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class UserControllerTest {

   public static final long ID = 1L;
   public static final String MSN_RECORD_IN_USER = String.format("%s of code %d cannot be deleted as it has links to other tables", User.class, ID);
   public static final String NAME = "Fulano";
   public static final String EMAIL = "fulano@yahoo.com.br";
   public static final String PASSWORD = "5415";
   public static final String OBJECT_NOT_FOUND = "Object not found.";
   public static final int INDEX = 0;
   public static final String MAIL_ALREADY_EXISTS = "E-mail already exists!";

   @InjectMocks
   private UserController userController;

   @Mock
   private UserServiceImpl userService;

   @Mock
   private ModelMapper mapper;
   private User user;
   private UserDTO userDTO;


   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
      startUP();
   }

   @Test
   void whenFindByIdThenReturnSuccess() {
      when(userService.findById(anyLong())).thenReturn(user);
      when(mapper.map(any(), any())).thenReturn(userDTO);

      val response = userController.findById(ID);

      assertNotNull(response);
      assertNotNull(response.getBody());
      assertEquals(ResponseEntity.class, response.getClass());
      assertEquals(UserDTO.class, response.getBody().getClass());
      assertEquals(ID, response.getBody().getId());
      assertEquals(NAME, response.getBody().getName());
      assertEquals(PASSWORD, response.getBody().getPassword());
      assertEquals(EMAIL, response.getBody().getEmail());

   }

   @Test
   void whenFindAllThenReturnAlistOfUserDTO() {
      when(userService.findAll()).thenReturn(List.of(user));
      when(mapper.map(any(), any())).thenReturn(userDTO);

      ResponseEntity<List<UserDTO>> response = userController.findAll();

      assertNotNull(response);
      assertNotNull(response.getBody());
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(ArrayList.class, response.getBody().getClass());
      assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());
      assertEquals(ID, response.getBody().get(INDEX).getId());
      assertEquals(NAME, response.getBody().get(INDEX).getName());
      assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
      assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
   }

   @Test
   void whenCreateThenRetornCreated() {
      when(userService.create(any())).thenReturn(user);
      ResponseEntity<UserDTO> response = userController.create(userDTO);

      assertNotNull(response);
      assertEquals(ResponseEntity.class, response.getClass());
      assertEquals(HttpStatus.CREATED, response.getStatusCode());

   }

   @Test
   void whenUpdateThenRetornSuccess() {
      when(userService.update(any())).thenReturn(user);
      ResponseEntity<UserDTO> response = userController.update(userDTO, ID);

      assertNotNull(response);
      assertEquals(ResponseEntity.class, response.getClass());
      assertEquals(HttpStatus.OK, response.getStatusCode());
   }

   @Test
   void whenDeleteThenRetornSuccess() {
      doNothing().when(userService).delete(anyLong());
      val response = userController.delete(ID);
      verify(userService, times(1)).delete(anyLong());
      assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
   }

   void startUP() {
      user = new User(ID, NAME, EMAIL, PASSWORD);
      userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
   }
}