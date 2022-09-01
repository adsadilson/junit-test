package br.com.apss.junittest.api.exceptions;

import lombok.Getter;

@Getter
public enum ProblemType {

   DADOS_INVALIDOS("/date-invalid", "Date invalid"),
   ERRO_DE_SISTEMA("/error-system", "System error"),
   PARAMETRO_INVALIDO("/parameter-invalid", "Parameter invalid"),
   MENSAGEM_INCOMPREENSIVEL("/incomprehensible-message\"", "Incomprehensible message"),
   RECURSO_NAO_ENCONTRADO("/resource-not-found", "Resource not found"),
   ENTIDADE_EM_USO("/entity-in-use", "Entity in use"),
   ERRO_NEGOCIO("/error-business", "Business rule violation");

   private String title;
   private String uri;

   ProblemType(String path, String title) {
      this.uri = "https://apssystem.com.br";
      this.title = title;
   }
}
