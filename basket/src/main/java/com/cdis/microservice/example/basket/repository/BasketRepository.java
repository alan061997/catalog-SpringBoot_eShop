package com.cdis.microservice.example.basket.repository;

import com.cdis.microservice.example.basket.model.BasketItem;
import com.cdis.microservice.example.basket.model.BasketModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends MongoRepository<BasketModel, String> {
    BasketModel findBasketModelBy_id(Object _id);
    BasketModel findBasketModelByUserId(Long id);
    void deleteByUserId(Long userId);
}
