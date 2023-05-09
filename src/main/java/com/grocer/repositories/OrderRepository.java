package com.grocer.repositories;

import com.grocer.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    public Optional<Order> findById(Integer id);
    public Optional<Order> findByNumber(Integer number);
}