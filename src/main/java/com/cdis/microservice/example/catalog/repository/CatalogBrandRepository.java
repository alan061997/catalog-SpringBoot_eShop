package com.cdis.microservice.example.catalog.repository;

import com.cdis.microservice.example.catalog.model.CatalogBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogBrandRepository extends JpaRepository<CatalogBrand, Long> {
}
