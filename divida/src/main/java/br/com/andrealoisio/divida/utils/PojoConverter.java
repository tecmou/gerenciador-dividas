package br.com.andrealoisio.divida.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PojoConverter {

    @Autowired
    private ModelMapper mapper;

    public <D> D convert(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
