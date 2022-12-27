package com.api.domain.services;


import com.api.domain.entities.EquipmentReading;
import com.api.domain.entities.exceptions.EntityInUseException;
import com.api.domain.entities.exceptions.EntityNotFoundException;
import com.api.repositories.EquipmentReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentReadingService {
    public static final String MSG_EQUIPMENT_READING_NOT_FOUND = "Resource with id %s not found!";
    @Autowired
    EquipmentReadingRepository repository;

    public EquipmentReading findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_EQUIPMENT_READING_NOT_FOUND, id)));
    }

    public List<EquipmentReading> findAll() {
        return repository.findAll();
    }

    public EquipmentReading save(EquipmentReading measure) {
        return repository.save(measure);
    }

//    public void delete(Long id) {
//        if (repository.findById(id).isEmpty()) {
//            throw new EntityNotFoundException("Data not found! ");
//        }
//        repository.deleteById(id);
//    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Resource with id %s is in use. Can't be deleted.", id));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(MSG_EQUIPMENT_READING_NOT_FOUND, id));
        }
    }

    public void update(EquipmentReading data) {
        repository.findById(data.getId());
        repository.save(data);
    }
}
