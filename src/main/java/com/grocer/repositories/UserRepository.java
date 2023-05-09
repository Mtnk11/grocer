package com.grocer.repositories;

import com.grocer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findById(int id);

    Optional<User> findByEmail(String email);
}
