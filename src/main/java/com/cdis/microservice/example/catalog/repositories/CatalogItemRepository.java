package com.cdis.microservice.example.catalog.repositories;

import com.cdis.microservice.example.catalog.models.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
    public Page<CatalogItem> findCatalogItemsByCatalogBrand_Id(Long id, Pageable pageable);

    public Page<CatalogItem> findCatalogItemsByCatalogType_Id(Long id, Pageable pageable);

    public Page<CatalogItem> findCatalogItemByCatalogBrand_IdAndCatalogType_Id(Long brand_id, Long type_id, Pageable pageable);

    public List<CatalogItem> findCatalogItemByName(String name);
}
