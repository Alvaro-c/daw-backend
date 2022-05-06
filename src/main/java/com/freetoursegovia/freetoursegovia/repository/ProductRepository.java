package com.freetoursegovia.freetoursegovia.repository;

import com.freetoursegovia.freetoursegovia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
