package com.crud.app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Service;

@Service
public class CSVService {
    private final InventoryRepository repository;
    
    CSVService(InventoryRepository repository) {
        this.repository = repository;
    }

    public ByteArrayInputStream load() {
        List<InventoryModel> inventory = repository.findAll();

        ByteArrayInputStream in =inventoryToCSV(inventory);
        return in;
    }

    private ByteArrayInputStream inventoryToCSV(List<InventoryModel> inventory) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
        for (InventoryModel item : inventory) {
            List<String> data = Arrays.asList(
                String.valueOf(item.getItemId()),
                item.getName(),
                String.valueOf(item.getCount()),
                String.valueOf(item.getPrice())
                );

            csvPrinter.printRecord(data);
        }
        csvPrinter.flush();
        return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
        throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
