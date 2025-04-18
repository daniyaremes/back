package com.watchstore.watchstorebackend.Service;


import com.watchstore.watchstorebackend.Entity.CartItem;
import com.watchstore.watchstorebackend.Entity.Watch;
import com.watchstore.watchstorebackend.Repository.CartItemRepository;
import com.watchstore.watchstorebackend.Repository.WatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final WatchRepository watchRepository;

    public CartService(CartItemRepository cartItemRepository, WatchRepository watchRepository) {
        this.cartItemRepository = cartItemRepository;
        this.watchRepository = watchRepository;
    }

    public List<CartItem> getCartItems(String sessionId) {
        return cartItemRepository.findBySessionId(sessionId);
    }

    public void addToCart(String sessionId, Long watchId, int quantity) {
        Watch watch = watchRepository.findById(watchId)
                .orElseThrow(() -> new RuntimeException("Watch not found"));
        if (quantity > watch.getStockQuantity()) {
            throw new RuntimeException("Not enough stock");
        }
        CartItem existingItem = cartItemRepository.findBySessionIdAndWatchId(sessionId, watchId);
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;
            if (newQuantity > watch.getStockQuantity()) {
                throw new RuntimeException("Exceeds stock");
            }

            existingItem.setQuantity(newQuantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem item = new CartItem();
            item.setWatch(watch);
            item.setQuantity(quantity);
            item.setSessionId(sessionId);
            cartItemRepository.save(item);
        }
    }


    public void updateCartItem(String sessionId, Long itemId, int quantity) {
        CartItem item = cartItemRepository.findByIdAndSessionId(itemId, sessionId)
                .orElseThrow(() -> new RuntimeException("Item not found or unauthorized"));
        if (quantity <= 0) {
            removeFromCart(sessionId, itemId);
        } else {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
    }


    public void removeFromCart(String sessionId, Long itemId) {
        cartItemRepository.deleteByIdAndSessionId(itemId, sessionId);
    }

    public void clearCart(String sessionId) {
        cartItemRepository.deleteBySessionId(sessionId);
    }

    public double calculateTotal(String sessionId) {
        return getCartItems(sessionId).stream()
                .mapToDouble(item -> item.getQuantity() * item.getWatch().getPrice())
                .sum();
    }
}
