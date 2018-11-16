package com.cdis.microservice.example.catalog.service;

import com.cdis.microservice.example.catalog.model.CatalogType;
import com.cdis.microservice.example.catalog.repository.CatalogTypeRepository;
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
public class CatalogTypeServiceImplIntegrationTest {

    private CatalogTypeServiceImpl catalogTypeService;

    @Mock
    private CatalogTypeRepository catalogTypeRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        catalogTypeService = new CatalogTypeServiceImpl(catalogTypeRepository);
    }

    @Test
    public void whenValidId_thenTypeShouldBeFound() {
        //  given
        CatalogType type = new CatalogType("testType");
        given(catalogTypeRepository.findById(type.getId())).willReturn(Optional.of(type));

        // when
        CatalogType foundType = catalogTypeService.getTypebyId(type.getId());

        // then
        assertThat(foundType).isEqualTo(type);
    }

    @Test
    public void checkTypeList(){
        //given
        CatalogType type1 = new CatalogType("testType1");
        CatalogType type2 = new CatalogType("testType2");

        List<CatalogType> savedTypes = Lists.newArrayList(type1, type2);

        given(catalogTypeRepository.findAll()).willReturn(savedTypes);

        // when
        List<CatalogType> retrievedSavedTypes = catalogTypeService.getAllCatalogTypes();

        // then
        assertThat(retrievedSavedTypes).isEqualTo(savedTypes);

    }

    @Test
    public void checkDeletedTypeList(){
        //given
        CatalogType type1 = new CatalogType("testType1");
        CatalogType type2 = new CatalogType("testType2");

        List<CatalogType> savedTypes = Lists.newArrayList(type1);

        given(catalogTypeRepository.save(type1)).willReturn(type1);
        given(catalogTypeRepository.save(type2)).willReturn(type2);
        given(catalogTypeRepository.findAll()).willReturn(savedTypes);

        // when
        catalogTypeService.addCatalogType(type1);
        catalogTypeService.addCatalogType(type2);
        catalogTypeService.deletingCatalogTypeById(type1.getId());

        List<CatalogType> retrievedSavedTypes = catalogTypeService.getAllCatalogTypes();

        // then
        assertThat(retrievedSavedTypes).isEqualTo(savedTypes);

    }
}
