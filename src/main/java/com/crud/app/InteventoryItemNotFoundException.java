package com.crud.app;

public class InteventoryItemNotFoundException extends RuntimeException{
    InteventoryItemNotFoundException(Long id) {
        super("Could not find the inventory item with id: " + id);
    }
    
}
