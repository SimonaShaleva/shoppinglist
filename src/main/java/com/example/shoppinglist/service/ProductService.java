package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.CategoryNameEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    BigDecimal findTotalPrice();

    List<ProductViewModel> findByCategoryName(CategoryNameEnum drink);

    void buyProductById(Long id);

    void buyAll();
}
