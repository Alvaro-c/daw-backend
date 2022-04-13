package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.Product;
import com.freetoursegovia.freetoursegovia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts() { return productRepository.findAll(); }

    public Product saveProduct(Product product) { return productRepository.save(product); }

    public Product findProductById(int id) { return productRepository.findById(id).orElseThrow(); }

    public void deleteProduct(int id) { productRepository.deleteById(id); }


}
