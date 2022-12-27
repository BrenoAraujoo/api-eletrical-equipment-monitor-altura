package com.api.controllers;

import com.api.domain.entities.Equipment;
import com.api.domain.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/equipments",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentController {

    @Autowired
    private EquipmentService service;

    @GetMapping
    public List<Equipment> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable  Long id) {
        var equipment = service.findById(id);
        return ResponseEntity.ok(equipment);
    }

    @PostMapping
    public ResponseEntity<Equipment> save(@RequestBody Equipment equipment) {
        var entity = service.save(equipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Equipment> update(@RequestBody Equipment equipment) {
        service.update(equipment);
        return ResponseEntity.ok(equipment);
    }
}
