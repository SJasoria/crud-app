package com.crud.app;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrudAppController {
    private final InventoryRepository repository;
    
    CrudAppController(InventoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/inventory")
    List<InventoryModel> all() {
        return this.repository.findAll();
    }
    
    @PostMapping("/inventory")
    InventoryModel newEmployee(@RequestBody InventoryModel newInventoryItem) {
        return repository.save(newInventoryItem);
    }

    // Single item
    
    @GetMapping("/inventory/{id}")
    InventoryModel one(@PathVariable Long id) {    
        return repository.findById(id)
        .orElseThrow(() -> new InteventoryItemNotFoundException(id));
    }

    @PutMapping("/inventory/{id}")
    InventoryModel replaceEmployee(@RequestBody InventoryModel newInventoryItem, 
        @PathVariable Long id) {
        return repository.findById(id)
            .map(inventoryItem -> {
                inventoryItem.setName(newInventoryItem.getName());
                inventoryItem.setWarehouse(newInventoryItem.getWarehouse());
                inventoryItem.setCount(newInventoryItem.getCount());
                return repository.save(inventoryItem);
            }).orElseGet(() -> {
                return repository.save(newInventoryItem);
            });
    }

    @DeleteMapping("/inventory/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
