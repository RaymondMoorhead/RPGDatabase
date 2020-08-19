package com.rpgdatabase.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rpgdatabase.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
}
