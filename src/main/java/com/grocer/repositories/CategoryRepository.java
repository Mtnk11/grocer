package com.grocer.repositories;

import com.grocer.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Optional<Category> findById(int id);
    Optional<Category> findByName(String name);
}