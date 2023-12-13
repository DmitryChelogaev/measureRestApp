package ru.chelogaev.dm.measurementrestapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chelogaev.dm.measurementrestapp.dto.SensorDTO;
import ru.chelogaev.dm.measurementrestapp.repositories.SensorsRepository;

@Component
public class SensorDBValidator implements Validator {
    private SensorsRepository sensorsRepository;

    @Autowired
    public SensorDBValidator(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SensorDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) o;
        String sensorName = sensorDTO.getName();
        if (sensorsRepository.findByName(sensorName).isPresent()) {
            errors.rejectValue("name","", "sensor with same name already exists");
        }
    }
}
