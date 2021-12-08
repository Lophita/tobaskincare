package com.lophita.tobaskincare.persistence;

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

    public Product(String id, String name, String identifier, String notes, String type) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.notes = notes;
        this.type = type;
    }
}
