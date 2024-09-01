package com.csiszer.my_shop.repository;

import com.csiszer.my_shop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
