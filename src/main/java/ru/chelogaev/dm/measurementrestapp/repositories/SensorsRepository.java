package ru.chelogaev.dm.measurementrestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chelogaev.dm.measurementrestapp.models.SensorEntity;

import java.util.Optional;

public interface SensorsRepository extends JpaRepository<SensorEntity, Integer> {
    Optional<SensorEntity> findByName(String sensorName);
}
