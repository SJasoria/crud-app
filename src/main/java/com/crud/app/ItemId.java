// This clas is utility class to run opertions requiring item id only.
package com.crud.app;

public class ItemId {
    private Long id;

    ItemId() {}
    
    ItemId(Long id) {
        this.id= id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
