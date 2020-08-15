package com.epam.recommendationservice.services;

import com.epam.recommendationservice.models.Product;
import com.epam.recommendationservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService
{

    @Autowired
    private ProductRepository productRepository;

    /**
     * return 5 product from same group
     */
    public List<Product> getTop5Recommendation(Long productId)
    {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(value -> productRepository.findTop5ByGroup(value.getGroup())).orElse(null);
    }


}
