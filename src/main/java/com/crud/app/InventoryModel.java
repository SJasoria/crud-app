// This class has the data model for invetory

package com.crud.app;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class InventoryModel {
    private @Id @GeneratedValue Long inventoryId;
    private String name;
    private Long count;
    private Double price;
    @CreationTimestamp
    private Timestamp createTime;
    @UpdateTimestamp
    private Timestamp lastUpdated;

    public InventoryModel(){}

    public InventoryModel(String name, Long count, Double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public Long getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated() {
        this.lastUpdated = new Timestamp(System.currentTimeMillis());
    }
}
