package com.lophita.tobaskincare.persistence;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Document
public class Stock {
    @Id
    private String id;
    private String identifier;
    private String name;
    private LocalDateTime stockUpdated;
    private BigDecimal price;
    private String notes;
    private String urlNotes;
    @CreatedBy
    private String username;
    @CreatedDate
    private LocalDateTime createdTime;

    public Stock(String identifier, String name, LocalDateTime stockUpdated, BigDecimal price, String notes, String urlNotes, String username, LocalDateTime createdTime) {
        this.identifier = identifier;
        this.name = name;
        this.stockUpdated = stockUpdated;
        this.price = price;
        this.notes = notes;
        this.urlNotes = urlNotes;
        this.username = username;
        this.createdTime = createdTime;
    }
}
