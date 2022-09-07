package br.com.apss.junittest.api.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@AllArgsConstructor
@ControllerAdvice
public class ExcepitonHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<StanderError> entityNotFoundException(EntityNotFoundException ex,
                                                               HttpServletRequest request) {
      ProblemType problemType = ProblemType.ERRO_NEGOCIO;
      StanderError error = StanderError.builder()
              .timestamp(LocalDateTime.now())
              .status(HttpStatus.NOT_FOUND.value())
              .error(problemType.getTitle())
              .message(ex.getMessage())
              .path(problemType.getUri()+request.getRequestURI())
              .build();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
   }

   @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<StanderError> dataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                       HttpServletRequest request) {
      ProblemType problemType = ProblemType.ERRO_NEGOCIO;
      StanderError error = StanderError.builder()
              .timestamp(LocalDateTime.now())
              .status(HttpStatus.BAD_REQUEST.value())
              .error(problemType.getTitle())
              .message(ex.getMessage())
              .path(problemType.getUri()+request.getRequestURI())
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }

   @ExceptionHandler(RecordInUseException.class)
   public ResponseEntity<StanderError> recordInUseException(RecordInUseException ex,
                                                                       HttpServletRequest request) {
      ProblemType problemType = ProblemType.ERRO_NEGOCIO;
      StanderError error = StanderError.builder()
              .timestamp(LocalDateTime.now())
              .status(HttpStatus.BAD_REQUEST.value())
              .error(problemType.getTitle())
              .message(ex.getMessage())
              .path(problemType.getUri()+request.getRequestURI())
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }
}
