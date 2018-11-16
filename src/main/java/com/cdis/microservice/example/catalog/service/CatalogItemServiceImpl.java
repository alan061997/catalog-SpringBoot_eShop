package com.cdis.microservice.example.catalog.service;

import com.cdis.microservice.example.catalog.exception.ResourceNotFoundException;
import com.cdis.microservice.example.catalog.model.CatalogItem;
import com.cdis.microservice.example.catalog.repository.CatalogItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogItemServiceImpl implements CatalogItemService {

    private CatalogItemRepository catalogItemRepository;

    public CatalogItemServiceImpl(CatalogItemRepository catalogItemRepository) {
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
    public List<CatalogItem> getAllCatalogItems() {
        return catalogItemRepository.findAll();
    }

    @Override
    public List<CatalogItem> getAllCatalogItemsFiltered(Long brand_id, Long type_id) {
        return catalogItemRepository.findCatalogItemByCatalogBrand_IdAndCatalogType_Id(brand_id, type_id);
    }

    @Override
    public CatalogItem getCatalogItemByName(String name) {
        return catalogItemRepository.findCatalogItemByName(name);
    }

    @Override
    public CatalogItem getItembyId(Long id) {
        return catalogItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Catalog Brand", "id", id));
    }

    @Override
    public List<CatalogItem> getAllCatalogItemsByBrand(Long brand_id) {
        return catalogItemRepository.findCatalogItemsByCatalogBrand_Id(brand_id);
    }

    @Override
    public List<CatalogItem> getAllCatalogItemsByType(Long type_id) {
        return catalogItemRepository.findCatalogItemsByCatalogType_Id(type_id);
    }

}
