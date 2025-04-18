package com.watchstore.watchstorebackend.controller;

import com.watchstore.watchstorebackend.Entity.Watch;
import com.watchstore.watchstorebackend.Service.WatchService;
import com.watchstore.watchstorebackend.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MainController {

    private final WatchService watchService;
    private final CartService cartService;

    @Autowired
    public MainController(WatchService watchService, CartService cartService) {
        this.watchService = watchService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Получаем топ-6 часов для главной страницы
        List<Watch> featuredWatches = watchService.getFeaturedWatches(6);
        model.addAttribute("watches", featuredWatches);

        // Кол-во товаров в корзине (для иконки в шапке)
        model.addAttribute("cartCount", cartService.getCartItemsCount());

        return "index"; // Возвращает шаблон index.html
    }
}