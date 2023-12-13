package ru.chelogaev.dm.measurementrestapp.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Sensor")
public class SensorEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "should not be empty")
    @Size(min = 2, max = 30, message = "should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "sensor")
    List<MeasureEntity> measureEntityList;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<MeasureEntity> getMeasureEntityList() {
        return measureEntityList;
    }

    public void setMeasureEntityList(List<MeasureEntity> measureEntityList) {
        this.measureEntityList = measureEntityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
