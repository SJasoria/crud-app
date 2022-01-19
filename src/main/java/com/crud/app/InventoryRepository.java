package com.crud.app;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryModel, CompositeKey>{
    Optional<InventoryModel> findByItemId(Long id);
    Optional<InventoryModel> findByName(String name);
    @Transactional
    void deleteByItemId(Long id);
}
