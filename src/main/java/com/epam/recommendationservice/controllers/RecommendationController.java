package com.epam.recommendationservice.controllers;

import com.epam.recommendationservice.models.Product;
import com.epam.recommendationservice.models.User;
import com.epam.recommendationservice.services.RecommendationService;
import org.springframework.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController
{
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/recommends")
    public List<Product> getRecommendedProductsId(@Nullable @RequestParam String user, @RequestParam Long productId)
    {

        return recommendationService.getTop5Recommendation(productId);
    }
}
