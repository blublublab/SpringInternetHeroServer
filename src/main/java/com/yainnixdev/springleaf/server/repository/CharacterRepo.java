package com.yainnixdev.springleaf.server.repository;


import com.yainnixdev.springleaf.server.domain.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepo extends CrudRepository<Character, String> {


}
