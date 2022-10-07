package com.api.controllers;


import com.api.entities.ReadData;
import com.api.services.ReadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/readdata", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReadDataController {

    @Autowired
    ReadDataService service;

    @GetMapping
    public ResponseEntity<List<ReadData>> findAll() {
        List<ReadData> list = service.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<ReadData> findById(@PathVariable Long id) {
        ReadData data = service.findById(id);
        return ResponseEntity.ok().body(data);
    }

    @PostMapping
    public ResponseEntity<ReadData> save(@RequestBody ReadData data) {
        service.save(data);
        return new ResponseEntity<ReadData>(data, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(
            value = "/{id}")
    public ResponseEntity<ReadData> update(@RequestBody ReadData data) {
        service.update(data);
        return ResponseEntity.ok(data);

    }

}
