package com.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Table(name= "tb_read_data")
public class ReadData implements Serializable {

    private static final long serialVersionUID = 8705916799906125269L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private Equipment equipment;
    private Double sensorA;
    private Double sensorB;
    private Double sensorC;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant dateTime;

    public ReadData() {
    }

    public ReadData(Long id, Equipment equipment ,Double sensorA, Double sensorB, Instant dateTime){
        this.id = id;
        this.equipment = equipment;
        this.sensorA = sensorA;
        this.sensorB = sensorB;
        this.dateTime = dateTime;

    }
    public ReadData(Long id, Equipment equipment, Double sensorA, Double sensorB,Double sensorC, Instant dateTime){
        this.id = id;
        this.equipment = equipment;
        this.sensorA = sensorA;
        this.sensorB = sensorB;
        this.sensorC= sensorC;
        this.dateTime = dateTime;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSensorA() {
        return sensorA;
    }

    public void setSensorA(Double sensorA) {
        this.sensorA = sensorA;
    }

    public Double getSensorB() {
        return sensorB;
    }

    public void setSensorB(Double sensorB) {
        this.sensorB = sensorB;
    }

    public Double getSensorC() {
        return sensorC;
    }

    public void setSensorC(Double sensorC) {
        this.sensorC = sensorC;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }
}
