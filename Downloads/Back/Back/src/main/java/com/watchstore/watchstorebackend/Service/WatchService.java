package com.watchstore.watchstorebackend.Service;


import com.watchstore.watchstorebackend.Entity.Watch;
import com.watchstore.watchstorebackend.Repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchService {
    private final WatchRepository watchRepository;

    @Autowired
    public WatchService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    public List<Watch> findAllWatches() {
        return watchRepository.findAll();
    }

    public Optional<Watch> findWatchById(Long id) {
        return watchRepository.findById(id);
    }

    public Watch saveWatch(Watch watch) {
        return watchRepository.save(watch);
    }

    public void deleteWatch(Long id) {
        watchRepository.deleteById(id);
    }

    // Фильтрация
    public List<Watch> filterWatches(Specification<Watch> spec) {
        return watchRepository.findAll(spec);
    }

    public List<Watch> findByBrand(String brand) {
        return watchRepository.findByBrandContainingIgnoreCase(brand);
    }

    public List<Watch> findByPriceRange(double min, double max) {
        return watchRepository.findByPriceBetween(min, max);
    }

    public List<Watch> findByType(Watch.WatchType type) {
        return watchRepository.findByType(type);
    }
}
