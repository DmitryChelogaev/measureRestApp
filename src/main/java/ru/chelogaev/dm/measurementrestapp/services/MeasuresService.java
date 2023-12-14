package ru.chelogaev.dm.measurementrestapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.chelogaev.dm.measurementrestapp.exceptions.EntityValidateException;
import ru.chelogaev.dm.measurementrestapp.models.MeasureEntity;
import ru.chelogaev.dm.measurementrestapp.models.SensorEntity;
import ru.chelogaev.dm.measurementrestapp.repositories.MeasuresRepository;

import javax.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional (readOnly = false)
public class MeasuresService {
    private MeasuresRepository measuresRepository;
    private SensorsService sensorService;

    @Autowired
    public MeasuresService(MeasuresRepository measuresRepository, SensorsService sensorService) {
        this.measuresRepository = measuresRepository;
        this.sensorService = sensorService;
    }

    public List<MeasureEntity> findAll(String sortBy, String order) {
        return sortBy != null ? ((order.toUpperCase().equals("DESC")) ? measuresRepository.findAll(Sort.by(sortBy).descending()) :
                measuresRepository.findAll(Sort.by(sortBy).ascending())) : measuresRepository.findAll();
    }

    @Transactional
    public MeasureEntity addMeasure(MeasureEntity measureEntity) {
        measureEntity.setCreatedAt(LocalDateTime.now());
        measureEntity.setUpdatedAt(LocalDateTime.now());
        measureEntity.setSensor(sensorService.findByName(measureEntity.getSensor().getName()));
        return measuresRepository.save(measureEntity);
    }

    public MeasureEntity findOne(Integer id) throws EntityNotFoundException{
        Optional<MeasureEntity> measure = measuresRepository.findById(id);
        return measure.orElseThrow(()->new EntityNotFoundException("Measure with id = "+id +" not found"));
    }

    @Transactional
    public MeasureEntity updateMeasure(MeasureEntity updatedMesure, int id) throws EntityNotFoundException, EntityValidateException{
        Optional<MeasureEntity> measure = measuresRepository.findById(id);
        if (updatedMesure.getSensor() == null) {
            throw new EntityValidateException("Measure not contains sensor");
        }
        if (measure.isPresent()) {
            MeasureEntity measureEntity = measure.get();
            measureEntity.setValue(updatedMesure.getValue());
            measureEntity.setRaining(updatedMesure.isRaining());
            measureEntity.setSensor(sensorService.findByName(updatedMesure.getSensor().getName()));
            measureEntity.setUpdatedAt(LocalDateTime.now());
            measuresRepository.save(measureEntity);
            return measureEntity;
        } else throw(new EntityNotFoundException("Measure with id = "+id +" not found"));
    }

    @Transactional
    public void delete(int id) {
        measuresRepository.deleteById(id);
    }

    public List<MeasureEntity> findAllWithPagination(Integer page, Integer records_per_page, String sortBy, String order) throws IllegalArgumentException{
        if (page<1) {
            throw new IllegalArgumentException("page number must be greater than 0");
        }
        return sortBy != null ? (order.toUpperCase().equals("DESC") ? measuresRepository.findAll(PageRequest.of(page-1, records_per_page, Sort.by(sortBy).descending())).getContent() :
                measuresRepository.findAll(PageRequest.of(page-1, records_per_page, Sort.by(sortBy).ascending())).getContent()) :
                measuresRepository.findAll(PageRequest.of(page-1, records_per_page)).getContent();
    }
}
