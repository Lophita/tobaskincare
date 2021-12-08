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
public class Transaction {
    @Id
    private String id;
    private LocalDateTime trxTime;
    private LocalDateTime expeditionTime;
    private BigDecimal price;
    private String notes;
    private SellingType type;
    private String identifier;
    private String name;


    @CreatedBy
    private String username;
    @CreatedDate
    private LocalDateTime createdTime;

    public Transaction(String id, LocalDateTime trxTime, LocalDateTime expeditionTime, BigDecimal price, String notes, SellingType type, String identifier, String name, String username, LocalDateTime createdTime) {
        this.id = id;
        this.trxTime = trxTime;
        this.expeditionTime = expeditionTime;
        this.price = price;
        this.notes = notes;
        this.type = type;
        this.identifier = identifier;
        this.name = name;
        this.username = username;
        this.createdTime = createdTime;
    }
}
