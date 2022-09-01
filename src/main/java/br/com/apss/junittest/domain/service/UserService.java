package br.com.apss.junittest.domain.service;

import br.com.apss.junittest.domain.model.User;

public interface UserService {
   User findById(Long id);
}
