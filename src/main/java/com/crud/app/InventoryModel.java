package com.crud.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.crud.app.util.CompositeKey;

@Entity
@IdClass(CompositeKey.class)
public class InventoryModel {
    private @Id @GeneratedValue Long itemId;
    private @Id String name;
    private Long count;
    private Double price;

    public InventoryModel(){}

    public InventoryModel(String name, Long count, Double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
