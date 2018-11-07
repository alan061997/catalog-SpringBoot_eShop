package com.cdis.microservice.example.catalog.service;

import com.cdis.microservice.example.catalog.exception.ResourceNotFoundException;
import com.cdis.microservice.example.catalog.model.CatalogBrand;
import com.cdis.microservice.example.catalog.repository.CatalogBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogBrandServiceImpl implements CatalogBrandService {

    private CatalogBrandRepository catalogBrandRepository;

    @Autowired
    public CatalogBrandServiceImpl(final CatalogBrandRepository catalogBrandRepository) {
        this.catalogBrandRepository = catalogBrandRepository;
    }

    @Override
    public CatalogBrand addCatalogBrand(CatalogBrand catalogBrand) {
        return catalogBrandRepository.save(catalogBrand);
    }

    @Override
    public void deletingCatalogBrandById(Long id) {
        catalogBrandRepository.deleteById(id);
    }

    @Override
    public List<CatalogBrand> getAllCatalogBrands() {
        return catalogBrandRepository.findAll();
    }

    @Override
    public CatalogBrand getBrandbyId(Long id) {
        return catalogBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog Brand", "id", id));
    }
}
