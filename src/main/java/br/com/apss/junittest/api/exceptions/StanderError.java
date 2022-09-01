package br.com.apss.junittest.api.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class StanderError {
   private LocalDateTime timestamp;
   private Integer status;
   private String error;
   private String message;
   private String path;

}
