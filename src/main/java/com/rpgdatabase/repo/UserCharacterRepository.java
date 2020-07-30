package com.rpgdatabase.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rpgdatabase.entity.UserCharacter;

public interface UserCharacterRepository extends MongoRepository<UserCharacter, String> {

	public List<UserCharacter> findAllByCreatorName(String creatorName);
}
