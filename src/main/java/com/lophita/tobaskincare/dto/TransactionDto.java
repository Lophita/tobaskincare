package com.lophita.tobaskincare.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.lophita.tobaskincare.persistence.SellingType;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class TransactionDto {

    private String id;
    private LocalDateTime trxTime;
    private LocalDateTime expeditionTime;
    private BigDecimal price;
    private String notes;
    private SellingType type;
    private String identifier;
    private String name;
    private String username;
    private LocalDateTime createdTime;

    @JsonCreator
    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    public TransactionDto(String id, LocalDateTime trxTime, LocalDateTime expeditionTime, BigDecimal price,
                          String notes, SellingType type, String identifier, String name, String username, LocalDateTime createdTime) {
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
