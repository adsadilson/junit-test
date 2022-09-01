package br.com.apss.junittest.core.profile;

import br.com.apss.junittest.domain.model.User;
import br.com.apss.junittest.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

   @Autowired
   private UserRepository userRepository;

   @Bean
   public void startDB(){
      User user1 = User.builder().name("Adsadilson").email("adsdilson@yahoo.com.br").password("254").build();
      User user2 = User.builder().name("Renato").email("renato@yahoo.com.br").password("58se254").build();
      userRepository.saveAll(List.of(user1,user2));
   }
}
