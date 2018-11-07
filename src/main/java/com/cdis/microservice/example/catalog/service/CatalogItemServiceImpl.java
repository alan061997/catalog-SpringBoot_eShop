package com.cdis.microservice.example.catalog.service;

import com.cdis.microservice.example.catalog.exception.ResourceNotFoundException;
import com.cdis.microservice.example.catalog.model.CatalogItem;
import com.cdis.microservice.example.catalog.repository.CatalogItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogItemServiceImpl implements CatalogItemService {

    private CatalogItemRepository catalogItemRepository;

    @Autowired
    public CatalogItemServiceImpl(final CatalogItemRepository catalogItemRepository) {
        this.catalogItemRepository = catalogItemRepository;
    }

    @Override
    public CatalogItem addCatalogItem(CatalogItem catalogItem) {
        return catalogItemRepository.save(catalogItem);
    }

    @Override
    public void deletingCatalogItemById(Long id) {
        catalogItemRepository.deleteById(id);
    }

    @Override
    public Page<CatalogItem> getAllCatalogItems(Pageable pageable) {
        return catalogItemRepository.findAll(pageable);
    }

    @Override
    public Page<CatalogItem> getAllCatalogItemsFiltered(Long brand_id, Long type_id, Pageable pageable) {
        return catalogItemRepository.findCatalogItemByCatalogBrand_IdAndCatalogType_Id(brand_id, type_id, pageable);
    }

    @Override
    public List<CatalogItem> getCatalogItemByName(String name) {
        return catalogItemRepository.findCatalogItemByName(name);
    }

    @Override
    public CatalogItem getItembyId(Long id) {
        return catalogItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Catalog Brand", "id", id));
    }

    @Override
    public Page<CatalogItem> getAllCatalogItemsByBrand(Long brand_id, Pageable pageable) {
        return catalogItemRepository.findCatalogItemsByCatalogBrand_Id(brand_id, pageable);
    }

    @Override
    public Page<CatalogItem> getAllCatalogItemsByType(Long type_id, Pageable pageable) {
        return catalogItemRepository.findCatalogItemsByCatalogType_Id(type_id, pageable);
    }

}
