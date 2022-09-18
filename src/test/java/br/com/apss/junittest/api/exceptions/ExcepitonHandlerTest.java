package br.com.apss.junittest.api.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExcepitonHandlerTest {

   @InjectMocks
   private ExcepitonHandler excepitonHandler;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   void whenEntityNotFoundExceptionThenRetornResponseEntity() {
      ResponseEntity<StanderError> response = excepitonHandler.entityNotFoundException(new EntityNotFoundException("Business rule violation"), new MockHttpServletRequest());
      assertNotNull(response);
      assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
      assertEquals("Business rule violation", response.getBody().getError());
      assertEquals(404, response.getBody().getStatus());
      assertEquals("Business rule violation", response.getBody().getMessage());
   }

   @Test
   void whenDataIntegrityViolationExceptionThenRetornResponseEntity() {
      ResponseEntity<StanderError> response = excepitonHandler.dataIntegrityViolationException(new DataIntegrityViolationException("Business rule violation"), new MockHttpServletRequest());
      assertNotNull(response);
      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("Business rule violation", response.getBody().getError());
      assertEquals(400, response.getBody().getStatus());
   }

   @Test
   void whenRecordInUseExceptionThenRetornResponseEntity() {
      ResponseEntity<StanderError> response = excepitonHandler.recordInUseException(new RecordInUseException("Business rule violation"), new MockHttpServletRequest());
      assertNotNull(response);
      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("Business rule violation", response.getBody().getError());
      assertEquals(400, response.getBody().getStatus());
   }

}