package com.crud.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
// @IdClass(CompositeKey.class)
public class InventoryModel {
    private @Id @GeneratedValue Long id;
    private String name;
    private String warehouse;
    private Long count;

    public InventoryModel(String name, String warehouse, Long count) {
        this.name = name;
        this.warehouse = warehouse;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
