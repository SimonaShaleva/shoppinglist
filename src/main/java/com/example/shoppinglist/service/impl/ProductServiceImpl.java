package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.CategoryNameEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.CategoryService;
import com.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);
        product.setCategory(categoryService.findByCategoryNameEnum(productServiceModel.getCategory()));
        productRepository.save(product);
    }

    @Override
    public BigDecimal findTotalPrice() {
        if (productRepository.findTotalSum() == null) {
            return BigDecimal.ZERO;
        }
        return productRepository.findTotalSum();
    }

    @Override
    public List<ProductViewModel> findByCategoryName(CategoryNameEnum category) {
        List<ProductServiceModel> list = productRepository
                .findByCategory_CategoryNameEnum(category)
                .stream().map(product -> modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
        return list.stream()
                .map(productServiceModel -> modelMapper.map(productServiceModel, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        productRepository.deleteAll();
    }
}
