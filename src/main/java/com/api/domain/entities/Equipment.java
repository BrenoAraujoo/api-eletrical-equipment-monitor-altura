package com.api.domain.entities;


import com.api.domain.entities.enums.MeasurementType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import java.io.Serializable;

@Entity
@Table(name = "tb_equipment")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer measurementType;

    public Equipment() {
    }

    public Equipment(Long id, String description, MeasurementType measurementType) {
        this.id = id;
        this.description = description;
        setMeasurementType(measurementType);
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MeasurementType getMeasurementType() {
        return MeasurementType.valueOf(measurementType);
    }

//    public Integer getMeasurementType(){
//        return measurementType;
//    }

    public void setMeasurementType(MeasurementType measurementType) {
        if(measurementType != null)
            this.measurementType = measurementType.getCode();
    }
}
