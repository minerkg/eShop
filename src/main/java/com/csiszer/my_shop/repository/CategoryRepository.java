package com.csiszer.my_shop.repository;

import com.csiszer.my_shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category finByName(String name);

}
