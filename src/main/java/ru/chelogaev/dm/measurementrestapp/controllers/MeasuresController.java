package ru.chelogaev.dm.measurementrestapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chelogaev.dm.measurementrestapp.dto.MeasureDTO;
import ru.chelogaev.dm.measurementrestapp.exceptions.EntityValidateException;
import ru.chelogaev.dm.measurementrestapp.models.MeasureEntity;
import ru.chelogaev.dm.measurementrestapp.services.MeasuresService;
import ru.chelogaev.dm.measurementrestapp.util.DTOConverter;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ru.chelogaev.dm.measurementrestapp.exceptions.CheckForCreateException.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasuresController {

    private MeasuresService measuresService;
    private DTOConverter dtoConverter;

    @Autowired
    public MeasuresController(MeasuresService measuresService, DTOConverter dtoConverter) {
        this.measuresService = measuresService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping()
    public List<MeasureDTO> getAll(@RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "size", required = false) Integer size,
                                   @RequestParam(value = "sort_by", required = false) String sortBy,
                                   @RequestParam(value = "order", defaultValue = "asc") String order) {
        if (page != null && size != null) {
            return measuresService.findAllWithPagination(page, size, sortBy, order).stream()
                    .map(x -> dtoConverter.convertToDTO(x, MeasureDTO.class)).collect(Collectors.toList());
        } else {
            return measuresService.findAll(sortBy, order).stream().map(x -> dtoConverter.convertToDTO(x, MeasureDTO.class)).collect(Collectors.toList());
        }
    }

    @GetMapping("/{id}")
    public MeasureDTO getMeasure(@PathVariable Integer id) throws EntityNotFoundException {
        return dtoConverter.convertToDTO(measuresService.findOne(id), MeasureDTO.class);
    }

    @GetMapping("/rainydayscount")
    public long getRainyDaysCount() {
        return measuresService.findAll(null, null).stream().filter(MeasureEntity::isRaining).count();
    }

    @PostMapping
    public MeasureDTO addMeasure(@RequestBody @Valid MeasureDTO measureDTO, BindingResult bindingResult) throws EntityValidateException {
        returnErrorsToClient(bindingResult);
        return dtoConverter.convertToDTO(measuresService.addMeasure(dtoConverter.convertToEntity(measureDTO, MeasureEntity.class)), MeasureDTO.class);
    }

    @PutMapping("/{id}")
    public MeasureDTO updateMeasure(@RequestBody @Valid MeasureDTO measureDTO, BindingResult bindingResult, @PathVariable Integer id) throws EntityNotFoundException, EntityValidateException {
        returnErrorsToClient(bindingResult);
        return dtoConverter.convertToDTO(measuresService.updateMeasure(dtoConverter.convertToEntity(measureDTO, MeasureEntity.class), id), MeasureDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(int id) {
        measuresService.delete(id);
    }

}
