package com.yainnixdev.springleaf.server.repository;


import com.yainnixdev.springleaf.server.domain.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepo extends CrudRepository<Hero, String> {
    Hero findByUser_Id(String user_id);
}
