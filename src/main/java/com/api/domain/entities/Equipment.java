package com.api.domain.entities;


import com.api.domain.entities.enums.MeasurementType;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "tb_equipment")
@Data
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
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
