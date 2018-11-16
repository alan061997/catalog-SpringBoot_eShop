package com.cdis.microservice.example.catalog.repository;


import com.cdis.microservice.example.catalog.model.CatalogBrand;
import com.cdis.microservice.example.catalog.model.CatalogItem;
import com.cdis.microservice.example.catalog.model.CatalogType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CatalogItemRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @Test
    public void whenFindItemByBrandId_thenReturnItems(){
        // Given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        entityManager.persist(testBrand);
        entityManager.flush();

        CatalogType testType = new CatalogType("testType");
        entityManager.persist(testType);
        entityManager.flush();

        CatalogItem testItem = new CatalogItem("testItem", "testDescription", 10, "pictureNameTest.png", "pictureFileURITest", testType, testBrand, 12);
        entityManager.persist(testItem);
        entityManager.flush();

        // when
        List<CatalogItem> foundItemsWithBrand = catalogItemRepository.findCatalogItemsByCatalogBrand_Id(testItem.getCatalogBrand().getId());

        // then
        for (CatalogItem items: foundItemsWithBrand) {
            Assert.assertTrue("Incorrect CatalogBrand", items.getCatalogBrand().getId().equals(testItem.getCatalogBrand().getId()));
        }
    }

    @Test
    public void whenFindItemByTypeId_thenReturnItems(){
        // Given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        entityManager.persist(testBrand);
        entityManager.flush();

        CatalogType testType = new CatalogType("testType");
        entityManager.persist(testType);
        entityManager.flush();

        CatalogItem testItem = new CatalogItem("testItem", "testDescription", 10, "pictureNameTest.png", "pictureFileURITest", testType, testBrand, 12);
        entityManager.persist(testItem);
        entityManager.flush();

        // when
        List<CatalogItem> foundItemsWithType = catalogItemRepository.findCatalogItemsByCatalogBrand_Id(testItem.getCatalogBrand().getId());

        // then
        for (CatalogItem items: foundItemsWithType) {
            Assert.assertTrue("Incorrect CatalogType", items.getCatalogType().getId().equals(testItem.getCatalogType().getId()));
        }
    }

    @Test
    public void whenFindItemByBrandIdAndTypeId_thenReturnItems(){
        // Given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        entityManager.persist(testBrand);
        entityManager.flush();

        CatalogType testType = new CatalogType("testType");
        entityManager.persist(testType);
        entityManager.flush();

        CatalogItem testItem = new CatalogItem("testItem", "testDescription", 10, "pictureNameTest.png", "pictureFileURITest", testType, testBrand, 12);
        entityManager.persist(testItem);
        entityManager.flush();

        // when
        List<CatalogItem> foundItemsWithBrand = catalogItemRepository.findCatalogItemsByCatalogBrand_Id(testItem.getCatalogBrand().getId());

        // then
        for (CatalogItem items: foundItemsWithBrand) {
            Assert.assertTrue("Incorrect CatalogBrand", items.getCatalogBrand().getId().equals(testItem.getCatalogBrand().getId()));
            Assert.assertTrue("Incorrect CatalogType", items.getCatalogType().getId().equals(testItem.getCatalogType().getId()));


        }
    }

    @Test
    public void whenFindItemByName_thenReturnItems(){
        // Given
        CatalogBrand testBrand = new CatalogBrand("testBrand");
        entityManager.persist(testBrand);
        entityManager.flush();

        CatalogType testType = new CatalogType("testType");
        entityManager.persist(testType);
        entityManager.flush();

        CatalogItem testItem = new CatalogItem("testItem", "testDescription", 10, "pictureNameTest.png", "pictureFileURITest", testType, testBrand, 12);
        entityManager.persist(testItem);
        entityManager.flush();

        // when
        CatalogItem foundItemsWithName = catalogItemRepository.findCatalogItemByName(testItem.getName());

        // then
        Assert.assertEquals(foundItemsWithName.getName(), testItem.getName());
    }
}
