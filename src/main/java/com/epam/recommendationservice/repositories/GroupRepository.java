package com.epam.recommendationservice.repositories;


import com.epam.recommendationservice.models.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<ProductGroup, Long> { }