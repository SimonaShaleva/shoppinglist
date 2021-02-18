package com.example.shoppinglist.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private CategoryNameEnum categoryNameEnum;
    private String description;

    public Category() {
    }

    public Category(CategoryNameEnum categoryNameEnum, String description) {
        this.categoryNameEnum = categoryNameEnum;
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "category_name_enum", unique = true, nullable = false)
    public CategoryNameEnum getCategoryNameEnum() {
        return categoryNameEnum;
    }

    public Category setCategoryNameEnum(CategoryNameEnum categoryNameEnum) {
        this.categoryNameEnum = categoryNameEnum;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
