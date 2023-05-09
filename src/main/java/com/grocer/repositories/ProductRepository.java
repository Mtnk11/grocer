package com.grocer.repositories;

import com.grocer.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
 public Optional<Product> findById(int id);
 public Optional<Product> findByName(String name);
}