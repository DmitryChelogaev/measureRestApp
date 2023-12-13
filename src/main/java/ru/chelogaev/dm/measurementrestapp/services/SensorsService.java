package ru.chelogaev.dm.measurementrestapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ru.chelogaev.dm.measurementrestapp.models.MeasureEntity;
import ru.chelogaev.dm.measurementrestapp.models.SensorEntity;
import ru.chelogaev.dm.measurementrestapp.repositories.SensorsRepository;

import javax.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<SensorEntity> findAll(String sortBy, String order) {
        return sortBy != null ? ((order.toUpperCase().equals("DESC")) ? sensorsRepository.findAll(Sort.by(sortBy).descending()) :
                sensorsRepository.findAll(Sort.by(sortBy).ascending())) : sensorsRepository.findAll();
    }

    public List<SensorEntity> findAllWithPagination(Integer page, Integer records_per_page, String sortBy, String order) throws IllegalArgumentException {
        if (page < 1) {
            throw new IllegalArgumentException("page number must be greater than 0");
        }
        return sortBy != null ? (order.toUpperCase().equals("DESC") ? sensorsRepository.findAll(PageRequest.of(page - 1, records_per_page, Sort.by(sortBy).descending())).getContent() :
                sensorsRepository.findAll(PageRequest.of(page - 1, records_per_page, Sort.by(sortBy).ascending())).getContent()) :
                sensorsRepository.findAll(PageRequest.of(page - 1, records_per_page)).getContent();
    }

    @Transactional
    public SensorEntity addSensor(SensorEntity measure) {
        return sensorsRepository.save(measure);
    }

    public SensorEntity findOne(Integer id) throws EntityNotFoundException{
        Optional<SensorEntity> sensor = sensorsRepository.findById(id);
        if (sensor.isPresent()) return sensor.get();
        else throw (new EntityNotFoundException("Sensor with id = " + id + " not found"));
    }

    @Transactional
    public SensorEntity updateSensor(SensorEntity updatedSensore, int id) throws EntityNotFoundException{
        Optional<SensorEntity> sensor = sensorsRepository.findById(id);
        if (sensor.isPresent()) {
            SensorEntity sensorEntity = sensor.get();
            sensorEntity.setUpdatedAt(LocalDateTime.now());
            sensorEntity.setName(updatedSensore.getName());
            sensorsRepository.save(sensorEntity);
            return sensorEntity;
        } else throw (new EntityNotFoundException("Sensor with id = " + id + " not found"));
    }

    @Transactional
    public void delete(int id) {
        sensorsRepository.deleteById(id);
    }

    public SensorEntity findByName(String name) throws EntityNotFoundException {
        Optional<SensorEntity> sensorEntityOpt = sensorsRepository.findByName(name);
        if (sensorEntityOpt.isPresent()) {
            return sensorEntityOpt.get();
        } else { throw (new EntityNotFoundException(String.format("Sensor with name ='%s' not found", name)));}
    }
}
