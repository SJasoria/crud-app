package com.crud.app;

public class InventoryItemNotFoundException extends RuntimeException{
    InventoryItemNotFoundException(Long id) {
        super("Could not find the inventory item with id: " + id);
    }
    
}
