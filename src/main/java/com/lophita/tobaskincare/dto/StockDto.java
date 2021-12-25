package com.lophita.tobaskincare.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockDto {

    private String id;
    @NotBlank(message = "Identifier can not be empty")
    private String identifier;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stockUpdated;
    @NotNull(message = "Price can not be null")
    private BigDecimal price;
    private String notes;
    private String urlSeller;
    @NotBlank(message = "Username can not be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Username must be alphabetic")
    private String username;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
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
}
