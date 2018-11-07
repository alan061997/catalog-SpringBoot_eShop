package com.cdis.microservice.example.basket.service;

import com.cdis.microservice.example.basket.model.BasketItem;
import com.cdis.microservice.example.basket.model.BasketModel;
import com.cdis.microservice.example.basket.repository.BasketRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BasketServiceImpl implements BasketService {

    public Logger logger = LoggerFactory.getLogger(BasketServiceImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public List<BasketModel> findAllBaskets() {
        return basketRepository.findAll();
    }

    @Override
    public BasketModel findBasketByUserId(Long userId) {
        return basketRepository.findBasketModelByUserId(userId);
    }

    @Override
    public BasketModel createBasket(BasketModel basketModel) {
        return basketRepository.save(basketModel);
    }

    @Override
    public BasketItem addItemToBasketOfUser(BasketItem basketItem, Long userId) {

        // Preparing Data
        BasketModel basketModel = basketRepository.findBasketModelByUserId(userId);
        logger.info("OLD BASKET = [ " + basketModel.toString() + " ]");

        List<BasketItem> basketItemList = basketModel.getItemList();

        List<BasketItem> newItemList;

        if(basketItemList == null) {
            newItemList = new ArrayList<BasketItem>();;
        } else {
            newItemList = basketItemList;
        }


        logger.info("LIST SIZE = " + newItemList.size());
        for(int i = 0; i < newItemList.size(); i++){
            logger.info("ID_1 =" + newItemList.get(i).getItemId());
            logger.info("ID_2 =" + basketItem.getItemId());
            if(newItemList.get(i).getItemId() == basketItem.getItemId()){
                logger.info("Item found in user basket");
                int newQuantity = newItemList.get(i).getQuantity() + basketItem.getQuantity();
                newItemList.get(i).setQuantity(newQuantity);
                logger.info("new item quantity = " + newItemList.get(i).getQuantity());
                basketModel.setItemList(newItemList);
                logger.info("BASKET = [" + basketModel.toString() + " ]");
                return basketItem;
            }
        }
        logger.info("Item not found in user basket");
        newItemList.add(basketItem);
        basketModel.setItemList(newItemList);
        logger.info("BASKET = [" + basketModel.toString() + " ]");
        basketRepository.save(basketModel);
        return basketItem;
    }

    @Override
    public void deleteBaskets() {
        basketRepository.deleteAll();
    }

    @Override
    public void deleteBasketbyUserId(Long userId) {
        basketRepository.deleteByUserId(userId);
    }

    @Override
    public BasketItem findItemInBasket(Long userId, Long itemId) {
        BasketModel basket = findBasketByUserId(userId);
        List<BasketItem> itemList = basket.getItemList();

        for(BasketItem item: itemList){
            if(item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void deleteItemInBasket(Long userId, Long itemId) {
        BasketModel basket= findBasketByUserId(userId);
        List<BasketItem> itemList = basket.getItemList();

        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getItemId() == itemId) {
                itemList.remove(i);
                break;
            }
        }
        basket.setItemList(itemList);
        basketRepository.save(basket);
    }
}
