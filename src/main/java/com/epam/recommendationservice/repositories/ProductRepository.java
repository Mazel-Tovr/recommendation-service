package com.epam.recommendationservice.repositories;


import com.epam.recommendationservice.models.Product;
import com.epam.recommendationservice.models.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop5ByGroup(ProductGroup productGroup);
}