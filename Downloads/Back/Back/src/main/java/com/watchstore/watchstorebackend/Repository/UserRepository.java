package com.watchstore.watchstorebackend.Repository;

import com.watchstore.watchstorebackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}