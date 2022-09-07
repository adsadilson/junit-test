package br.com.apss.junittest.api.exceptions;

public class EntityNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public EntityNotFoundException(String mensagem) {
      super(mensagem);
   }

   public EntityNotFoundException(String classe, Long id) {
      super(String.format("There is no record of %s with code %d", classe, id));
   }
}
