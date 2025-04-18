package com.watchstore.watchstorebackend.Repository;


import com.watchstore.watchstorebackend.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findBySessionIdAndWatchId(String sessionId, Long watchId);
    Optional<CartItem> findByIdAndSessionId(Long id, String sessionId);
    List<CartItem> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
    void deleteByIdAndSessionId(Long id, String sessionId);
}

