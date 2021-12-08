package com.lophita.tobaskincare.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class StockDto {

    private String id;
    private String identifier;
    private String name;
    private LocalDateTime stockUpdated;
    private BigDecimal price;
    private String notes;
    private String urlSeller;
    private String username;
    private LocalDateTime createdTime;

    public StockDto(String id, String identifier, String name, LocalDateTime stockUpdated, BigDecimal price, String notes, String urlSeller, String username, LocalDateTime createdTime) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.stockUpdated = stockUpdated;
        this.price = price;
        this.notes = notes;
        this.urlSeller = urlSeller;
        this.username = username;
        this.createdTime = createdTime;
    }
}
