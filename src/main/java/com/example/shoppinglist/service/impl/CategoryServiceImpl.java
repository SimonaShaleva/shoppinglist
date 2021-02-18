package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryNameEnum;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() != CategoryNameEnum.values().length) {
            for (CategoryNameEnum name : CategoryNameEnum.values()) {
                this.categoryRepository.save(new Category(name,
                        String.format("This is description for %s", name)));
            }
        }
    }

    @Override
    public Category findByCategoryNameEnum(CategoryNameEnum category) {
        return this.categoryRepository.findByCategoryNameEnum(category).orElse(null);
    }
}
