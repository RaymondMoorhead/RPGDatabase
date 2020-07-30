package com.rpgdatabase.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rpgdatabase.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	  public User findByUsername(String username);
}
