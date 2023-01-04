package com.api.domain.entities;


import com.api.core.validation.Groups;
import com.api.domain.entities.enums.MeasurementType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "tb_equipment")
@Data
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String cod;
    @NotBlank
    private String name;
    private String description;

//    @ConvertGroup(to = Groups.MeasurementTypeCod.class)
//    @Valid
    private Integer measurementType;

    public void setMeasurementType(MeasurementType measurementType) {
        if(measurementType != null)
            this.measurementType = measurementType.getCode();
    }


}
