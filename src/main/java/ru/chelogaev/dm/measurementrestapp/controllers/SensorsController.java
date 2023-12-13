package ru.chelogaev.dm.measurementrestapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chelogaev.dm.measurementrestapp.dto.SensorDTO;
import ru.chelogaev.dm.measurementrestapp.exceptions.EntityValidateException;
import ru.chelogaev.dm.measurementrestapp.models.SensorEntity;
import ru.chelogaev.dm.measurementrestapp.services.SensorsService;
import ru.chelogaev.dm.measurementrestapp.util.DTOConverter;
import ru.chelogaev.dm.measurementrestapp.util.SensorDBValidator;

import static ru.chelogaev.dm.measurementrestapp.exceptions.CheckForCreateException.returnErrorsToClient;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private SensorsService sensorsService;
    private SensorDBValidator sensorDBValidator;
    private DTOConverter dtoConverter;

    @Autowired
    public SensorsController(SensorsService sensorsService, SensorDBValidator sensorDBValidator, DTOConverter dtoConverter) {
        this.sensorsService = sensorsService;
        this.sensorDBValidator = sensorDBValidator;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public List<SensorDTO> getAll(@RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "records_per_page", required = false) Integer records_per_page,
                                   @RequestParam(value = "sort_by", required = false) String sortBy,
                                   @RequestParam(value = "order", defaultValue = "ASC") String order)    {
        if (page != null && records_per_page != null) {
            return sensorsService.findAllWithPagination(page, records_per_page, sortBy, order).stream()
                    .map(x -> dtoConverter.convertToDTO(x, SensorDTO.class)).collect(Collectors.toList());
        } else {
            return sensorsService.findAll(sortBy, order).stream().map(x -> dtoConverter.convertToDTO(x, SensorDTO.class)).collect(Collectors.toList());
        }
    }

    @PostMapping
    public SensorDTO addCensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) throws EntityValidateException {
      sensorDBValidator.validate(sensorDTO, bindingResult);//Same name verification
      returnErrorsToClient(bindingResult);
      return dtoConverter.convertToDTO(sensorsService.addSensor(dtoConverter.convertToEntity(sensorDTO, SensorEntity.class)), SensorDTO.class);
    }

    @PutMapping("{/id})")
    public SensorDTO editCensor(@RequestParam int id, @RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) throws EntityValidateException {
        sensorDBValidator.validate(sensorDTO, bindingResult);//Same name verification
        returnErrorsToClient(bindingResult);
        SensorEntity upSens = sensorsService.updateSensor(dtoConverter.convertToEntity(sensorDTO, SensorEntity.class), id);
        return dtoConverter.convertToDTO(upSens, SensorDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@RequestParam int id) {
        sensorsService.delete(id);
    }


}
