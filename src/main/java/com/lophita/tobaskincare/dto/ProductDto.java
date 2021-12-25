package com.lophita.tobaskincare.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductDto {
    private String id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Identifier can not be empty")
    private String identifier;
    private String notes;
    @NotBlank(message = "Type can not be empty")
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
