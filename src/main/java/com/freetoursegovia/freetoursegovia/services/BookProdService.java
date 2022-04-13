package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.BookProd;
import com.freetoursegovia.freetoursegovia.model.BookProdId;
import com.freetoursegovia.freetoursegovia.repository.BookProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookProdService {

    @Autowired
    BookProdRepository bookProdRepository;

    public List<BookProd> findAllBookProd() { return bookProdRepository.findAll(); }

    public BookProd saveBookProd(BookProd bookProd) { return bookProdRepository.save(bookProd); }

    public BookProd findBookProdById(BookProdId id) { return bookProdRepository.findById(id).orElseThrow(); }

    public void deleteBookProd(BookProdId id) { bookProdRepository.deleteById(id); }

 }

