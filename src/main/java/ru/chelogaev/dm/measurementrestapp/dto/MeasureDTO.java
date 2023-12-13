package ru.chelogaev.dm.measurementrestapp.dto;

import ru.chelogaev.dm.measurementrestapp.models.MeasureEntity;

import javax.validation.constraints.*;
import java.util.function.Function;

public class MeasureDTO{
    @NotNull(message = "must be non empty")
    @Min(-100)
    @Max(100)
    private Float value;

    @NotNull(message = "must be non empty")
    private Boolean raining;

    private SensorDTO sensor;

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }
}
