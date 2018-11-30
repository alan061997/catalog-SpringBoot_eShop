package com.cdis.microservice.example.basket;


import com.cdis.microservice.example.basket.model.BasketItem;
import com.cdis.microservice.example.basket.model.BasketModel;
import com.cdis.microservice.example.basket.model.BasketUser;
import com.cdis.microservice.example.basket.repository.BasketRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataMongoTest
public class BasketRepositoryImplementationTest {

    @Mock
    private BasketRepository basketRepository;

    @Before
    public void setup(){
        BasketUser basketUser = new BasketUser(1L, "testUsername");
        BasketModel basketModel = new BasketModel(ObjectId.get(), basketUser.getUserId());
        BasketItem basketItem1 = new BasketItem(1L, 10, 11, false);
        BasketItem basketItem2 = new BasketItem(2L, 52, 3, false);

        List<BasketItem> basketItemList = new ArrayList<>();
        basketItemList.add(basketItem1);
        basketItemList.add(basketItem2);

        basketModel.setItemList(basketItemList);
        System.out.println(basketModel.toString());


        Mockito.when(basketRepository.findBasketModelByUserId(basketUser.getUserId())).thenReturn(basketModel);
    }

    @Test
    public void whenFindBasketByUserId_ReturnBasket(){
        // Given
        Long userId = 1L;

        // When
        BasketModel basketModel = basketRepository.findBasketModelByUserId(userId);

        // Then
        Assert.assertTrue(basketModel.getUserId().equals(userId));

    }

    @Test
    public void whenFindIteminUserBasket_ReturnItemInItemList(){
        // Given
        Long userId = 1L;
        Long itemId = 2L;

        // When
        BasketModel basketModel = basketRepository.findBasketModelByUserId(userId);
        List<BasketItem> basketItemList = basketModel.getItemList();

        for(BasketItem item: basketItemList){
            if(item.getItemId().equals(itemId)) {
                assert true;
                return;
            }
        }

        assert false;

    }
}
