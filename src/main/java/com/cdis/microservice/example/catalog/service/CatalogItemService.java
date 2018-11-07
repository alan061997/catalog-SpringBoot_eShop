package com.cdis.microservice.example.catalog.service;

import com.cdis.microservice.example.catalog.model.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogItemService {

    // Creating items for catalog
    CatalogItem addCatalogItem(CatalogItem catalogItem);

    // Deleting Item from catalog using Id
    void deletingCatalogItemById(final Long id);

    // Retrive Items from catalog
    Page<CatalogItem> getAllCatalogItems(Pageable pageable);

    // Retriev items with brand and type filters
    Page<CatalogItem> getAllCatalogItemsFiltered(Long brand_id, Long type_id, Pageable pageable);

    // Retrieve Item by Name
    List<CatalogItem> getCatalogItemByName(String name);

    // Retrieve Items by Id
    CatalogItem getItembyId(final Long id);

    // Retrieve Items by Brand
    Page<CatalogItem> getAllCatalogItemsByBrand(Long brand_id, Pageable pageable);

    // Retrieve Items by Brand
    Page<CatalogItem> getAllCatalogItemsByType(Long type_id, Pageable pageable);

}
