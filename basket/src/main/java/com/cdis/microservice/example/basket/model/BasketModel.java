package com.cdis.microservice.example.basket.model;

import com.mongodb.lang.Nullable;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@ToString
public class BasketModel {
    @Id
    private ObjectId _id;

    @NotNull(message = "User Id is required")
    private Long userId;


    private List<BasketItem> itemList = new ArrayList<>();


    public BasketModel() {
    }

    public BasketModel(ObjectId _id, Long userId) {
        this._id = _id;
        this.userId = userId;
    }

    public String getId() {
        return _id.toHexString();
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BasketItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<BasketItem> itemList) {
        this.itemList = itemList;
    }
}