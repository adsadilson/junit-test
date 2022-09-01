package br.com.apss.junittest.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

   @Id
   @GeneratedValue
   private Long id;

   @Column(length = 85)
   private String name;

   @Column(length = 256, unique = true)
   private String email;

   @Column(length = 36)
   private String password;

}
