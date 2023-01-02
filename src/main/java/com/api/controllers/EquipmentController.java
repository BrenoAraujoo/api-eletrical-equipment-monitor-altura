package com.api.controllers;

import com.api.domain.entities.Equipment;
import com.api.domain.services.EquipmentService;
import javax.validation.Valid;
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
    private EquipmentService equipmentService;

    @GetMapping
    public List<Equipment> findAll() {
        return equipmentService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Equipment findById(@PathVariable  Long id) {
        return equipmentService.findOrFail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment save(@RequestBody @Valid Equipment equipment) {
        var entity = equipmentService.save(equipment);
        return entity;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Equipment> update(@RequestBody Equipment equipment) {
        equipmentService.update(equipment);
        return ResponseEntity.ok(equipment);
    }
}
