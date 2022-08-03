package com.api.controllers;


import com.api.Application;
import com.api.entities.ReadData;
import com.api.services.ReadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/readdata")
public class ReadDataController {

    @Autowired
    ReadDataService service;

    @GetMapping(value = "{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReadData> findById(@PathVariable Long id){
        ReadData data = service.findById(id);
        return ResponseEntity.ok().body(data);
    }
    @GetMapping
    public ResponseEntity<List<ReadData>> findAll(){
      List<ReadData> list = service.findAll();
      return ResponseEntity.ok(list);
    }
}
