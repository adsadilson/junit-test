package br.com.apss.junittest.api.exceptions;

public class RecordInUseException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public RecordInUseException(String mensagem) {
      super(mensagem);
   }

   public RecordInUseException(String classe, Long id) {
      super(String.format("%s of code %d cannot be deleted as it has links to other tables", classe, id));
   }
}
