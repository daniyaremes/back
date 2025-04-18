package com.watchstore.watchstorebackend.Controller;

import com.watchstore.watchstorebackend.Service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        String sessionId = session.getId();
        model.addAttribute("cartItems", cartService.getCartItems(sessionId));
        model.addAttribute("total", cartService.calculateTotal(sessionId));
        return "cart/view";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long watchId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        cartService.addToCart(session.getId(), watchId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartItem(@RequestParam Long itemId,
                                 @RequestParam int quantity,
                                 HttpSession session) {
        cartService.updateCartItem(session.getId(), itemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long itemId,
                                 HttpSession session) {
        cartService.removeFromCart(session.getId(), itemId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        cartService.clearCart(session.getId());
        return "redirect:/cart";
    }
}
