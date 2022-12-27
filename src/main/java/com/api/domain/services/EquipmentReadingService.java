package com.api.domain.services;


import com.api.domain.entities.EquipmentReading;
import com.api.domain.entities.exceptions.EntityNotFoundException;
import com.api.repositories.EquipmentReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentReadingService {
    @Autowired
    EquipmentReadingRepository repository;

    public EquipmentReading findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measure not found!"));
    }

    public List<EquipmentReading> findAll() {
        return repository.findAll();
    }

    public EquipmentReading save(EquipmentReading measure) {
        return repository.save(measure);
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Data not found! ");
        }
        repository.deleteById(id);
    }

    public void update(EquipmentReading data) {
        repository.findById(data.getId());
        repository.save(data);
    }
}
