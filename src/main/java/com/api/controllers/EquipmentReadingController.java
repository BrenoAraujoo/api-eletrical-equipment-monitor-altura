package com.api.controllers;


import com.api.services.EquipmentReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/readdata", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentReadingController {

    @Autowired
    EquipmentReadingService service;

    @GetMapping
    public ResponseEntity<List<com.api.entities.EquipmentReading>> findAll() {
        List<com.api.entities.EquipmentReading> list = service.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<com.api.entities.EquipmentReading> findById(@PathVariable Long id) {
        com.api.entities.EquipmentReading data = service.findById(id);
        return ResponseEntity.ok().body(data);
    }

    @PostMapping
    public ResponseEntity<com.api.entities.EquipmentReading> save(@RequestBody com.api.entities.EquipmentReading data) {
        service.save(data);
        return new ResponseEntity<com.api.entities.EquipmentReading>(data, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(
            value = "/{id}")
    public ResponseEntity<com.api.entities.EquipmentReading> update(@RequestBody com.api.entities.EquipmentReading data) {
        service.update(data);
        return ResponseEntity.ok(data);

    }

}
