package com.lophita.tobaskincare.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    private String urlSeller;
    @CreatedBy
    private String username;
    @CreatedDate
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

    @Override
    public String toString() {
        return "Stock{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", stockUpdated=" + stockUpdated +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                ", urlNotes='" + urlSeller + '\'' +
                ", username='" + username + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
