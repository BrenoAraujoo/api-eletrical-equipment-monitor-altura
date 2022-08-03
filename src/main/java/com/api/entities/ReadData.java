package com.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.Instant;


@Entity
@Table(name= "tb_read_data")
public class ReadData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double ampere;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant dateTime;

    public ReadData() {
    }

    public ReadData(Long id, Double ampere, Instant dateTime){
        this.id = id;
        this.ampere = ampere;
        this.dateTime = dateTime;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmpere() {
        return ampere;
    }

    public void setAmpere(Double ampere) {
        this.ampere = ampere;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }
}
