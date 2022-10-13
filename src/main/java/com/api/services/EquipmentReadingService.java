package com.api.services;


import com.api.exceptions.ResourceNotFoundException;
import com.api.repositories.EquipmentReadingRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentReadingService {
    @Autowired
    EquipmentReadingRepository repository;

    public com.api.entities.EquipmentReading findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Measure not found!"));
    }

    public List<com.api.entities.EquipmentReading> findAll() {
        return repository.findAll();
    }

    public com.api.entities.EquipmentReading save(com.api.entities.EquipmentReading measure) {
        return repository.save(measure);
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Data not found! ");
        }
        repository.deleteById(id);
    }

    public void update(com.api.entities.EquipmentReading data) {
        repository.findById(data.getId());
        repository.save(data);
    }
}
