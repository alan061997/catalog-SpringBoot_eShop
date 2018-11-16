package com.cdis.microservice.example.catalog.service;

import com.cdis.microservice.example.catalog.model.CatalogBrand;
import com.cdis.microservice.example.catalog.model.CatalogItem;
import com.cdis.microservice.example.catalog.model.CatalogType;
import com.cdis.microservice.example.catalog.repository.CatalogItemRepository;
import org.assertj.core.util.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class CatalogItemServiceImplIntegrationTest {

    private CatalogItemServiceImpl catalogItemService;

    @Mock
    private CatalogItemRepository catalogItemRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        catalogItemService = new CatalogItemServiceImpl(catalogItemRepository);
    }


    @Test
    public void whenValidName_thenItemShouldBeFound() {
        //  given
        CatalogItem item = new CatalogItem("testItem", "testDescription", 10, "pictureNameTest.png", "pictureFileURITest", new CatalogType("testType"), new CatalogBrand("testBrand"), 12);
        given(catalogItemService.getCatalogItemByName("testItem")).willReturn(item);

        // when
        CatalogItem foundItem = catalogItemService.getCatalogItemByName("testItem");

        // then
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    public void whenValidId_thenItemShouldBeFound() {
        //  given
        CatalogItem item = new CatalogItem("testItem", "testDescription", 10, "pictureNameTest.png", "pictureFileURITest", new CatalogType("testType"), new CatalogBrand("testBrand"), 12);
        given(catalogItemRepository.findById(item.getId())).willReturn(Optional.of(item));

        // when
        CatalogItem foundItem = catalogItemService.getItembyId(item.getId());

        // then
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    public void whenValidBrandId_thenItemsShouldBeFound() {
        //  given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        CatalogItem item1 = new CatalogItem("testItem1", "testDescription1", 10, "pictureNameTest.png", "pictureFileURITest1", new CatalogType("testType"), testBrand, 12);
        CatalogItem item2 = new CatalogItem("testItem2", "testDescription2", 33, "pictureNameTest.png", "pictureFileURITest2", new CatalogType("testType"), testBrand, 10);
        List<CatalogItem> itemsByBrand = Lists.newArrayList(item1, item2);
        given(catalogItemRepository.findCatalogItemsByCatalogBrand_Id(testBrand.getId())).willReturn(itemsByBrand);

        // when
        List<CatalogItem> foundItems = catalogItemService.getAllCatalogItemsByBrand(testBrand.getId());

        // then
        assertThat(foundItems).isEqualTo(itemsByBrand);
    }

    @Test
    public void whenValidTypeId_thenItemsShouldBeFound() {
        //  given
        CatalogType testType= new CatalogType("testType");
        CatalogItem item1 = new CatalogItem("testItem1", "testDescription1", 10, "pictureNameTest.png", "pictureFileURITest1", testType, new CatalogBrand("testBrand"), 12);
        CatalogItem item2 = new CatalogItem("testItem2", "testDescription2", 33, "pictureNameTest.png", "pictureFileURITest2", testType, new CatalogBrand("testBrand"), 10);
        List<CatalogItem> itemsByType = Lists.newArrayList(item1, item2);
        given(catalogItemRepository.findCatalogItemsByCatalogType_Id(testType.getId())).willReturn(itemsByType);

        // when
        List<CatalogItem> foundItems = catalogItemService.getAllCatalogItemsByType(testType.getId());

        // then
        assertThat(foundItems).isEqualTo(itemsByType);
    }

    @Test
    public void whenValidTypeIdAndBrandId_thenItemsShouldBeFound(){
        //given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        CatalogType testType = new CatalogType("testType");
        CatalogItem item1 = new CatalogItem("testItem1", "testDescription1", 10, "pictureNameTest.png", "pictureFileURITest1", testType, testBrand, 12);
        CatalogItem item2 = new CatalogItem("testItem2", "testDescription2", 33, "pictureNameTest.png", "pictureFileURITest2", testType, testBrand, 10);

        List<CatalogItem> itemsByBrandAndType = Lists.newArrayList(item1, item2);
        given(catalogItemRepository.findCatalogItemByCatalogBrand_IdAndCatalogType_Id(testBrand.getId(), testType.getId())).willReturn(itemsByBrandAndType);

        // when
        List<CatalogItem> foundItems = catalogItemService.getAllCatalogItemsFiltered(testBrand.getId(), testType.getId());

        // then
        assertThat(foundItems).isEqualTo(itemsByBrandAndType);

    }

    @Test
    public void checkItemList(){
        //given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        CatalogType testType = new CatalogType("testType");
        CatalogItem item1 = new CatalogItem("testItem1", "testDescription1", 10, "pictureNameTest.png", "pictureFileURITest1", testType, testBrand, 12);
        CatalogItem item2 = new CatalogItem("testItem2", "testDescription2", 33, "pictureNameTest.png", "pictureFileURITest2", testType, testBrand, 10);

        List<CatalogItem> savedItems = Lists.newArrayList(item1, item2);

        given(catalogItemRepository.findAll()).willReturn(savedItems);
        given(catalogItemRepository.save(item1)).willReturn(item1);
        given(catalogItemRepository.save(item2)).willReturn(item2);

        // when
        catalogItemService.addCatalogItem(item1);
        catalogItemService.addCatalogItem(item2);
        List<CatalogItem> retrievedSavedItems = catalogItemService.getAllCatalogItems();

        // then
        assertThat(retrievedSavedItems).isEqualTo(savedItems);

    }

    @Test
    public void checkDeletedItemList(){
        //given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        CatalogType testType = new CatalogType("testType");
        CatalogItem item1 = new CatalogItem("testItem1", "testDescription1", 10, "pictureNameTest.png", "pictureFileURITest1", testType, testBrand, 12);
        CatalogItem item2 = new CatalogItem("testItem2", "testDescription2", 33, "pictureNameTest.png", "pictureFileURITest2", testType, testBrand, 10);

        List<CatalogItem> savedItems = Lists.newArrayList(item1);

        given(catalogItemRepository.save(item1)).willReturn(item1);
        given(catalogItemRepository.save(item2)).willReturn(item2);
        given(catalogItemRepository.findAll()).willReturn(savedItems);

        // when
        catalogItemService.addCatalogItem(item1);
        catalogItemService.addCatalogItem(item2);
        catalogItemService.deletingCatalogItemById(item1.getId());

        List<CatalogItem> retrievedSavedItems = catalogItemService.getAllCatalogItems();

        // then
        assertThat(retrievedSavedItems).isEqualTo(savedItems);

    }
}
