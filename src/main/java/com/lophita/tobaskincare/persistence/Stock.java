package com.lophita.tobaskincare.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@Document
public class Stock {
    @Id
    private String id;
    private String identifier;
    private String name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stockUpdated;
    private BigDecimal price;
    private String notes;
    private String urlSeller;
    @CreatedBy
    private String username;
    @CreatedDate
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @JsonCreator
    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    public Stock(String id, String identifier, String name, LocalDateTime stockUpdated, BigDecimal price, String notes, String urlSeller, String username, LocalDateTime createdTime) {
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
