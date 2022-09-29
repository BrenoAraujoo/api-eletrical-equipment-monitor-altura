package com.api.repositories;

import com.api.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentRepository extends JpaRepository<Equipment, Long> {
}
