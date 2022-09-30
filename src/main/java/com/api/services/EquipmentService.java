package com.api.services;

import com.api.entities.Equipment;
import com.api.exceptions.ResourceNotFoundException;
import com.api.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentRepository repository;

    public List<Equipment> findAll(){
        return repository.findAll();
    }

    public Equipment findById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Equipment not found!"));
    }

    public Equipment save(Equipment equipment){
        return repository.save(equipment);
    }

    public void delete(Long id){
        if(repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Equipment not found");
        }
        repository.deleteById(id);
    }

    public void update(Equipment equipment) {
        repository.findById(equipment.getId());
        repository.save(equipment);
    }
}
