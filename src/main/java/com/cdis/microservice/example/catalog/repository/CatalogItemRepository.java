package com.cdis.microservice.example.catalog.repository;

import com.cdis.microservice.example.catalog.model.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
    Page<CatalogItem> findCatalogItemsByCatalogBrand_Id(Long id, Pageable pageable);

    Page<CatalogItem> findCatalogItemsByCatalogType_Id(Long id, Pageable pageable);

    Page<CatalogItem> findCatalogItemByCatalogBrand_IdAndCatalogType_Id(Long brand_id, Long type_id, Pageable pageable);

    List<CatalogItem> findCatalogItemByName(String name);
}
