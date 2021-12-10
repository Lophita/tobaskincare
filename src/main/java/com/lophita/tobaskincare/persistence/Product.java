package com.lophita.tobaskincare.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class Product {
    @Id
    private String id;
    private String name;
    private String identifier;
    private String notes;
    private String type;

    @JsonCreator
    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    public Product(String id, String name, String identifier, String notes, String type) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.notes = notes;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", notes='" + notes + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
