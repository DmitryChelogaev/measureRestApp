package ru.chelogaev.dm.measurementrestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chelogaev.dm.measurementrestapp.models.MeasureEntity;

public interface MeasuresRepository extends JpaRepository<MeasureEntity, Integer> {
}
