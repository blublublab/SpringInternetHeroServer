package com.yainnixdev.springleaf.server.repository;


import com.yainnixdev.springleaf.server.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,String> {
    User findByName(String name);
}