package ru.chelogaev.dm.measurementrestapp.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter{
    private ModelMapper modelMapper;

    @Autowired
    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T,E> E convertToEntity (T dto, Class<E>entityClass){
        return modelMapper.map(dto, entityClass);
    }

    public <T, E> T convertToDTO (E entity, Class<T> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}

