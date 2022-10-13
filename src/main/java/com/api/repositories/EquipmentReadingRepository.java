package com.api.repositories;

import com.api.domain.entities.EquipmentReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentReadingRepository extends JpaRepository<EquipmentReading,Long> {
}
