package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryNameEnum(CategoryNameEnum category);
}
