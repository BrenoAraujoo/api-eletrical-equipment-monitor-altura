package com.api.domain.entities;


import com.api.domain.entities.enums.MeasurementType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_equipment")
@Data
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cod;
    private String description;
    private Integer measurementType;



    public void setMeasurementType(MeasurementType measurementType) {
        if(measurementType != null)
            this.measurementType = measurementType.getCode();
    }
}
