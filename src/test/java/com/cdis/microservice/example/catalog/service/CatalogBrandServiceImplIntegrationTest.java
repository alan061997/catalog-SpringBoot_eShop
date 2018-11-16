package com.cdis.microservice.example.catalog.service;

import com.cdis.microservice.example.catalog.model.CatalogBrand;
import com.cdis.microservice.example.catalog.repository.CatalogBrandRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CatalogBrandServiceImplIntegrationTest {

    private CatalogBrandServiceImpl catalogBrandService;

    @Mock
    private CatalogBrandRepository catalogBrandRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        catalogBrandService = new CatalogBrandServiceImpl(catalogBrandRepository);
    }

    @Test
    public void whenValidId_thenBrandShouldBeFound() {
        //  given
        CatalogBrand brand = new CatalogBrand("testBrand");
        given(catalogBrandRepository.findById(brand.getId())).willReturn(Optional.of(brand));

        // when
        CatalogBrand foundBrand = catalogBrandService.getBrandbyId(brand.getId());

        // then
        assertThat(foundBrand).isEqualTo(brand);
    }

    @Test
    public void checkBrandList(){
        //given
        CatalogBrand brand1 = new CatalogBrand("testBrand1");
        CatalogBrand brand2 = new CatalogBrand("testBrand2");

        List<CatalogBrand> savedBrands = Lists.newArrayList(brand1, brand2);

        given(catalogBrandRepository.findAll()).willReturn(savedBrands);

        // when
        List<CatalogBrand> retrievedSavedBrands = catalogBrandService.getAllCatalogBrands();

        // then
        assertThat(retrievedSavedBrands).isEqualTo(savedBrands);

    }

    @Test
    public void checkDeletedBrandList(){
        //given
        CatalogBrand brand1 = new CatalogBrand("testBrand1");
        CatalogBrand brand2 = new CatalogBrand("testBrand2");

        List<CatalogBrand> savedBrands = Lists.newArrayList(brand1);

        given(catalogBrandRepository.save(brand1)).willReturn(brand1);
        given(catalogBrandRepository.save(brand2)).willReturn(brand2);
        given(catalogBrandRepository.findAll()).willReturn(savedBrands);

        // when
        catalogBrandService.addCatalogBrand(brand1);
        catalogBrandService.addCatalogBrand(brand2);
        catalogBrandService.deletingCatalogBrandById(brand1.getId());

        List<CatalogBrand> retrievedSavedBrands = catalogBrandService.getAllCatalogBrands();

        // then
        assertThat(retrievedSavedBrands).isEqualTo(savedBrands);

    }
}
