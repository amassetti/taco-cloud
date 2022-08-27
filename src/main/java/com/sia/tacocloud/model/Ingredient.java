package com.sia.tacocloud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Ingredient implements Persistable<String> {
    @Id
    private String id;
    private String name;

    private IngredientType type;

    public Ingredient() {
    }

    public Ingredient(String id) {
        this.id = id;
    }

    public Ingredient(String id, String name, IngredientType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
