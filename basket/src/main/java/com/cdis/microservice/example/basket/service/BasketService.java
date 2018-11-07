package com.cdis.microservice.example.basket.service;

import com.cdis.microservice.example.basket.model.BasketItem;
import com.cdis.microservice.example.basket.model.BasketModel;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface BasketService {
    List<BasketModel> findAllBaskets();
    BasketModel findBasketByUserId(Long userId);
    BasketModel createBasket(BasketModel basketModel);
    BasketItem addItemToBasketOfUser(BasketItem basketItem, Long userId);
    void deleteBaskets();
    void deleteBasketbyUserId(Long userId);
    BasketItem findItemInBasket(Long userId, Long itemId);
    void deleteItemInBasket(Long userId, Long itemId);
}
