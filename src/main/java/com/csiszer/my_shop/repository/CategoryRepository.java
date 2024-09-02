package com.csiszer.my_shop.repository;

import com.csiszer.my_shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    @Query("select c from Category c where c.name = :name")
    Category findByName(String name);


}
