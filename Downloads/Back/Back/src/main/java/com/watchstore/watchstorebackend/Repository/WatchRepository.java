package com.watchstore.watchstorebackend.Repository;


import com.watchstore.watchstorebackend.Entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long>, JpaSpecificationExecutor<Watch> {
    List<Watch> findByBrandContainingIgnoreCase(String brand);
    List<Watch> findByPriceBetween(double minPrice, double maxPrice);
    List<Watch> findByType(Watch.WatchType type);
}
