package com.rpgdatabase.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rpgdatabase.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	public boolean existsByUsername(String username);
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
}
