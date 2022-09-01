package br.com.apss.junittest.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "uer")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PESSOA_ID_SEQ")
   @EqualsAndHashCode.Include
   private Long id;

   @Column(length = 100)
   private String name;

   @EqualsAndHashCode.Include
   @Column(length = 256, unique = true)
   private String email;

   @Column(length = 32)
   private String password;

}
