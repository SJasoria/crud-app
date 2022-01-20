// This class contains API endpoints for CREATE, READ, UPDATE and DELETE
// Additionally it has API endpoint to download CSV

package com.crud.app;

import java.util.List;

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
     * @return inventory list model and view
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
        ModelAndView mav;
        repository.save(newInventoryItem);
        mav = new ModelAndView("add", "item", new InventoryModel());
        return mav;
    }

    /**
     * API to display add item form
     * @return edit item model and view
     */
    @GetMapping("/edit")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView("edit", "item", new InventoryModel());
        return mav;
    }
    
    /**
     * API to edit an existinf entry. If it finds the entry for the given 
     * inventory ID, it'll update it. Else it will re route to warning page
     * @param newInventoryItem edit model and view or warning page
     * @return
     */
    @PostMapping("/edit-item")
    public ModelAndView editItem(@ModelAttribute InventoryModel newInventoryItem) {
        return repository.findById(newInventoryItem.getInventoryId())
            .map(inventoryItem -> {
                inventoryItem.setName(newInventoryItem.getName());
                inventoryItem.setCount(newInventoryItem.getCount());
                inventoryItem.setPrice(newInventoryItem.getPrice());
                inventoryItem.setLastUpdated();
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
    
    /**
     * API tp delete table entries using inventory ID number.
     * @return delete form model and view
     */
    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView mav = new ModelAndView("delete", "itemId", new ItemId());
        return mav;
    }

    /**
     * API to delete existing entries from the table. If the entry does not exist
     * it'll redirect to the warning page.
     * @param itemId
     * @return delete page or warning page
     */
    @PostMapping("/delete-from-inventory")
    public ModelAndView deleteItem(@ModelAttribute("item") 
    ItemId itemId) {
        boolean itemExists = repository.existsById(itemId.getId());
        if(itemExists) {
            repository.deleteById(itemId.getId());
            return new ModelAndView("delete",  "itemId", new ItemId());
        } else {
            return new ModelAndView("item-not-exist");
        }
        
    }

    /**
     * Calls the CSV service to download the current inventory
     * @return CSV file
     */
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
