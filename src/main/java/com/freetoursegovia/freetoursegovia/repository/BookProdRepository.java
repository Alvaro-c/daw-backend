package com.freetoursegovia.freetoursegovia.repository;

import com.freetoursegovia.freetoursegovia.model.BookProd;
import com.freetoursegovia.freetoursegovia.model.BookProdId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookProdRepository extends JpaRepository<BookProd, BookProdId> {

}
