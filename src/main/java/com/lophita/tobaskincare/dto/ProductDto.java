package com.lophita.tobaskincare.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ProductDto {
    private String id;
    private String name;
    private String identifier;
    private String notes;
    private String type;

    @JsonCreator
    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    public ProductDto(String id, String name, String identifier, String notes, String type) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.notes = notes;
        this.type = type;
    }
}
