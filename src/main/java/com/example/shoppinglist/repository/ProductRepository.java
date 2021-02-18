package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.CategoryNameEnum;
import com.example.shoppinglist.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select sum(p.price) from Product p")
    BigDecimal findTotalSum();

    List<Product> findByCategory_CategoryNameEnum(CategoryNameEnum category);

    void deleteById(Long id);
}
