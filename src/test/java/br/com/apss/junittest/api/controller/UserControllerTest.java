package br.com.apss.junittest.api.controller;

import br.com.apss.junittest.api.dto.UserDTO;
import br.com.apss.junittest.domain.entity.User;
import br.com.apss.junittest.domain.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

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
   private UserController controller;

   @Mock
   private UserServiceImpl service;

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
   void findById() {
   }

   @Test
   void findAll() {
   }

   @Test
   void create() {
   }

   @Test
   void update() {
   }

   @Test
   void delete() {
   }

   void startUP() {
      user = new User(ID, NAME, EMAIL, PASSWORD);
      userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
   }
}