package com.crud.app;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CrudAppController {
    private final InventoryRepository repository;
    
    CrudAppController(InventoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/inventory-list")
    public ModelAndView inventoryList() {
        List<InventoryModel> itemList = this.repository.findAll();
        ModelAndView mav = new ModelAndView("inventory-list");
        mav.addObject("itemList", itemList);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("add", "item", new InventoryModel());
        return mav;
    }

    @PostMapping(value = "/add-to-inventory")
    public String newEmployee(@ModelAttribute("item") InventoryModel newInventoryItem) {
        repository.save(newInventoryItem);
        return "add";
    }
    
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
