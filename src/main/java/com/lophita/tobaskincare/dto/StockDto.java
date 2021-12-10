package com.lophita.tobaskincare.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class StockDto {

    private String id;
    @NotBlank
    private String identifier;
    @NotBlank
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime stockUpdated;
    private BigDecimal price;
    private String notes;
    private String urlSeller;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String username;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createdTime;

    @JsonCreator
    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
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

    @Override
    public String toString() {
        return "StockDto{" +
                "id='" + id + '\'' +
                ", identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", stockUpdated=" + stockUpdated +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                ", urlSeller='" + urlSeller + '\'' +
                ", username='" + username + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
