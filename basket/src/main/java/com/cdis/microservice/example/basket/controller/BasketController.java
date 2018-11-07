package com.cdis.microservice.example.basket.controller;

import com.cdis.microservice.example.basket.model.BasketItem;
import com.cdis.microservice.example.basket.model.BasketModel;
import com.cdis.microservice.example.basket.service.BasketService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/basket")
    public List<BasketModel> getAllBaskets(){
        return basketService.findAllBaskets();
    }

    @GetMapping("/basket/{userId}")
    public BasketModel getBasketbyUserId(@PathVariable("userId") final Long userId){
        return basketService.findBasketByUserId(userId);
    }


    @PostMapping("/basket")
    public BasketModel createBasket(@Valid @RequestBody BasketModel basketModel){
        basketModel.setId(ObjectId.get());
        List<BasketItem> basketItemList = new ArrayList<>();
        basketItemList.add(new BasketItem(1L, 10, 5, false));
        basketItemList.add(new BasketItem(2L, 13, 2, false));
        basketModel.setItemList(basketItemList);
        basketService.createBasket(basketModel);
        return basketModel;
    }

    @PostMapping("/basket/{userId}/item")
    public BasketItem addItemtoUserBasket(
            @Valid @RequestBody BasketItem basketItem,
            @PathVariable("userId") final Long userId){
        basketService.addItemToBasketOfUser(basketItem, userId);
        return basketItem;
    }

    @GetMapping("/basket/{userId}/item/{itemId}")
    public BasketItem findItemInUserBasket(
            @PathVariable("userId") final Long userId,
            @PathVariable("itemId") final Long itemId){
        return basketService.findItemInBasket(userId, itemId);
    }


    @DeleteMapping("/basket")
    public void deleteBaskets(){
        basketService.deleteBaskets();
    }

    @DeleteMapping("/basket/{userId}")
    public void deleteBasketbyUserId(@PathVariable("userId") final Long userId){
        basketService.deleteBasketbyUserId(userId);
    }

    @DeleteMapping("/basket/{userId}/item/{itemId}")
    public void deleteBasketItemInBasketById(
            @PathVariable("userId") final Long userId,
            @PathVariable("itemId") final Long itemId){
        basketService.deleteItemInBasket(userId, itemId);
    }
}
