package ru.chelogaev.dm.measurementrestapp.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "should not be empty")
    @Size(min = 2, max = 30, message = "should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
