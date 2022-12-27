package com.api.domain.services;

import com.api.domain.entities.Equipment;
import com.api.domain.entities.exceptions.EntityInUseException;
import com.api.domain.entities.exceptions.EntityNotFoundException;
import com.api.domain.entities.exceptions.EquipmentNotFoundException;
import com.api.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    public static final String MSG_EQUIPMENT_NOT_FOUND = "Equipment with id %s not found!";
    @Autowired
    EquipmentRepository repository;

    public List<Equipment> findAll() {
        return repository.findAll();
    }

    public Equipment findOrFail(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(
                        String.format(MSG_EQUIPMENT_NOT_FOUND, id)));
    }

    public Equipment save(Equipment equipment) {
        return repository.save(equipment);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format("Resource with id %s is in use. Can't be deleted.",id));
        }catch (EmptyResultDataAccessException e){
            throw new EquipmentNotFoundException(String.format(MSG_EQUIPMENT_NOT_FOUND,id));
        }
    }

    public void update(Equipment equipment) {
        repository.findById(equipment.getId());
        repository.save(equipment);
    }
}
