package br.com.andrealoisio.divida.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PojoConverter {

    private ModelMapper mapper = new ModelMapper();

    public <D> D convert(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }

    public <D> Optional<D> convert(Optional<? extends Object> source, Class<D> destinationType) {
        if (source.isEmpty()) {
            return Optional.empty();
        }
        D d = convert(source.get(), destinationType);
        return Optional.of(d);
    }

    public <D> List<D> convert(List<? extends Object> source, Class<D> destinationType) {
        return source.stream().map(d -> convert(d, destinationType)).collect(Collectors.toList());
    }
}
