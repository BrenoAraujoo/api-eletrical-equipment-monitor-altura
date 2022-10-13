package com.api.controllers;


import com.api.domain.entities.EquipmentReading;
import com.api.domain.services.EquipmentReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/equipmentReading",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentReadingController {

    @Autowired
    EquipmentReadingService service;

    @GetMapping
    public List<EquipmentReading> findAll() {
       return service.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipmentReading> findById(@PathVariable Long id) {
        EquipmentReading data = service.findById(id);
        return ResponseEntity.ok().body(data);
    }

    @PostMapping
    public ResponseEntity<EquipmentReading> save(@RequestBody EquipmentReading data) {
        service.save(data);
        return new ResponseEntity<EquipmentReading>(data, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(
            value = "/{id}")
    public ResponseEntity<EquipmentReading> update(@RequestBody EquipmentReading data) {
        service.update(data);
        return ResponseEntity.ok(data);

    }

}
