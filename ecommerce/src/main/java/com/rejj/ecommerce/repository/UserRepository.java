package com.rejj.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rejj.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email);

}
