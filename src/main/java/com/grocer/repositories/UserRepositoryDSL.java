package com.grocer.repositories;

import com.grocer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryDSL extends JpaRepository<User,Integer>, QuerydslPredicateExecutor<User> {
}
