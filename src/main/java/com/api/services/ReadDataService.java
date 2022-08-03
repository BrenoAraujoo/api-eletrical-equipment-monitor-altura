package com.api.services;


import com.api.entities.ReadData;
import com.api.repositories.ReadDataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadDataService {
    @Autowired
    ReadDataRepository repository;

    public ReadData findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Measure not found!"));
    }

    public List<ReadData> findAll() {
        return repository.findAll();
    }

    public ReadData save(ReadData measure) {
        return repository.save(measure);
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Measure not found! ");
        }
        repository.deleteById(id);
    }

    public void update(ReadData data) {
        repository.findById(data.getId());
        repository.save(data);
    }
}
