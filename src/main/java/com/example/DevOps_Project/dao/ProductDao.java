package com.example.DevOps_Project.dao;

import com.example.DevOps_Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDao extends JpaRepository<Product , Integer> {

}
