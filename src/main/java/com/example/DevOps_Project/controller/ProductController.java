package com.example.DevOps_Project.controller;


import com.example.DevOps_Project.dao.ProductDao;
import com.example.DevOps_Project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {


        @Autowired
        ProductDao  productDao;

        @GetMapping
        public List<Product> productList(){
                List<Product> products = productDao.findAll();
                return products;
        }

        @GetMapping(value = "/product/{id}")
        public Optional<Product> getAProduct(@PathVariable int id)
        {
                Optional<Product> product = productDao.findById(id);
                return product;
        }
}
