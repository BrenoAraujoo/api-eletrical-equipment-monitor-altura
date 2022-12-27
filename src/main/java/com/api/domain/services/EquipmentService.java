package com.api.domain.services;

import com.api.domain.entities.Equipment;
import com.api.repositories.EquipmentRepository;
import javax.persistence.EntityNotFoundException;
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
                .orElseThrow(()-> new EntityNotFoundException("Equipment not found!"));
    }

    public Equipment save(Equipment equipment){
        return repository.save(equipment);
    }

    public void delete(Long id){
        if(repository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Equipment not found");
        }
        repository.deleteById(id);
    }

    public void update(Equipment equipment) {
        repository.findById(equipment.getId());
        repository.save(equipment);
    }
}
