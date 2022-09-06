package br.com.apss.junittest.domain.service;

import br.com.apss.junittest.domain.entity.User;

import java.util.List;

public interface UserService {
   User findById(Long id);
   List<User> findAll();
}
