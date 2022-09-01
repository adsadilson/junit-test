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

   @ExceptionHandler(ObjectNotFoundException.class)
   public ResponseEntity<StanderError> objectNotFound(ObjectNotFoundException ex,
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
}
