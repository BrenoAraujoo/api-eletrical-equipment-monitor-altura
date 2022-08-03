package com.api.repositories;

import com.api.entities.ReadData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadDataRepository extends JpaRepository<ReadData,Long> {
}
