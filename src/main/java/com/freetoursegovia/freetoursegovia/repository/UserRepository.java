package com.freetoursegovia.freetoursegovia.repository;

import com.freetoursegovia.freetoursegovia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
