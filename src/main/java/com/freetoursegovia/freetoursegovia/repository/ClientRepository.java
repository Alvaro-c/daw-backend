package com.freetoursegovia.freetoursegovia.repository;

import com.freetoursegovia.freetoursegovia.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to extend Jpa Library
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {


}
