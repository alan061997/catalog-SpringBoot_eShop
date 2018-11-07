package com.cdis.microservice.example.catalog.repository;

import com.cdis.microservice.example.catalog.model.CatalogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogTypeRepository extends JpaRepository<CatalogType, Long> {
}
