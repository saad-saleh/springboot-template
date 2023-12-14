package com.spring.sample.mapper;


import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MapperHelper<S, T> {

    @Autowired
    private ModelMapper modelMapper;



    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public T entityToDto(S entity, Class<T> targetClass) {
        return modelMapper.map(entity, targetClass);
    }

    public T map(S entity, Class<T> targetClass) {
        return modelMapper.map(entity, targetClass);
    }


}
