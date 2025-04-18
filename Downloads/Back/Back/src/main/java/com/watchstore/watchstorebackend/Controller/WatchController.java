package com.watchstore.watchstorebackend.Controller;

import com.watchstore.watchstorebackend.Entity.Watch;
import com.watchstore.watchstorebackend.Service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/watches")
public class WatchController {
    private final WatchService watchService;

    @Autowired
    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @GetMapping
    public String listWatches(Model model) {
        model.addAttribute("watches", watchService.findAllWatches());
        return "watches/list";
    }

    @GetMapping("/{id}")
    public String viewWatch(@PathVariable Long id, Model model) {
        watchService.findWatchById(id).ifPresent(watch -> model.addAttribute("watch", watch));
        return "watches/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("watch", new Watch());
        return "watches/create";
    }

    @PostMapping
    public String createWatch(@ModelAttribute Watch watch) {
        watchService.saveWatch(watch);
        return "redirect:/watches";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        watchService.findWatchById(id).ifPresent(watch -> model.addAttribute("watch", watch));
        return "watches/edit";
    }

    @PostMapping("/{id}")
    public String updateWatch(@PathVariable Long id, @ModelAttribute Watch watch) {
        watch.setId(id);
        watchService.saveWatch(watch);
        return "redirect:/watches";
    }

    @PostMapping("/{id}/delete")
    public String deleteWatch(@PathVariable Long id) {
        watchService.deleteWatch(id);
        return "redirect:/watches";
    }

    @GetMapping("/filter")
    public String filterWatches(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Watch.WatchType type,
            Model model) {

        Specification<Watch> spec = Specification.where(null);

        if (brand != null && !brand.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
        }

        if (minPrice != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        if (type != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("type"), type));
        }

        model.addAttribute("watches", watchService.filterWatches(spec));
        return "watches/list";
    }
}