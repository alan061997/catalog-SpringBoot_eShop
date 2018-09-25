package com.cdis.microservice.example.catalog.events;

import lombok.*;

import java.io.Serializable;

@Data
public class CatalogSolvedEvent implements Serializable {
    private final Long catalogItemBuyId;
    private final Long userId;
    private final boolean bougth;

    public CatalogSolvedEvent(Long catalogItemBuyId, Long userId, boolean bougth) {
        this.catalogItemBuyId = catalogItemBuyId;
        this.userId = userId;
        this.bougth = bougth;
    }
}
