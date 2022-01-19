package com.crud.app;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CrudAppController {
    private final InventoryRepository repository;
    private final CSVService csvService;
    
    CrudAppController(InventoryRepository repository, CSVService csvService) {
        this.repository = repository;
        this.csvService = csvService;
    }

    /**
     * API to home page.
     * @return home view
     */
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

    /**
     * API to display all the items in the list.
     * @return inventory list view
     */
    @GetMapping("/inventory-list")
    public ModelAndView inventoryList() {
        List<InventoryModel> itemList = this.repository.findAll();
        ModelAndView mav = new ModelAndView("inventory-list");
        mav.addObject("itemList", itemList);
        return mav;
    }

    /**
     * API to display add item form
     * @return add item model and view
     */
    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("add", "item", new InventoryModel());
        return mav;
    }

    /**
     * API to add new item to the inventory.
     * @param newInventoryItem  new item to be added
     * @return add item model and view
     */
    @PostMapping(value = "/add-to-inventory")
    public ModelAndView newEmployee(@ModelAttribute("item") 
        InventoryModel newInventoryItem) {
        boolean itemExists = repository.findByName(newInventoryItem.getName())
            .isPresent();
        ModelAndView mav;
        if(!itemExists) {
            repository.save(newInventoryItem);
            mav = new ModelAndView("add", "item", new InventoryModel());
            return mav;
        } else {
            mav = new ModelAndView("add-item-exists");
            return mav;
        }
        
    }

    /**
     * API to display add item form
     * @return add item model and view
     */
    @GetMapping("/edit")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView("edit", "item", new InventoryModel());
        return mav;
    }
    
    @PostMapping("/edit-item")
    public ModelAndView editItem(@ModelAttribute InventoryModel newInventoryItem) {
        System.out.println("Inside the API");
        return repository.findById(newInventoryItem.getItemId())
            .map(inventoryItem -> {
                inventoryItem.setName(newInventoryItem.getName());
                inventoryItem.setCount(newInventoryItem.getCount());
                inventoryItem.setPrice(newInventoryItem.getPrice());
                repository.save(inventoryItem);
                ModelAndView mav 
                    = new ModelAndView("edit", "item", new InventoryModel());
                return mav;
            }).orElseGet(() -> {
                ModelAndView mav 
                    = new ModelAndView("item-not-exist");
                return mav;
            });
    }
    
    @Transactional
    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView mav = new ModelAndView("delete", "itemId", new ItemId());
        return mav;
    }

    @PostMapping("/delete-from-inventory")
    public ModelAndView deleteItem(@ModelAttribute("item") 
    ItemId itemId) {
        repository.deleteByItemId(itemId.getId());
        return new ModelAndView("delete",  "itemId", new ItemId());
    }

    @GetMapping("/downloadcsv")
    public ResponseEntity<Resource> getFile() {
        String filename = "inventory.csv";
        InputStreamResource file = new InputStreamResource(csvService.load());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/csv"))
            .body(file);
    }
}
