package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Devedor;
import br.com.andrealoisio.divida.persistence.DevedorEntity;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DevedorRepositoryImplForJpa implements DevedorRepository {

    @Autowired
    private JpaDevedorRepository jpaDevedorRepository;

    @Autowired
    private PojoConverter converter;

    @Override
    public List<Devedor> saveAll(List<Devedor> devedores) {
        List<DevedorEntity> devedoresEntity = devedores.stream().map(d -> converter.convert(d, DevedorEntity.class)).collect(Collectors.toList());
        devedoresEntity = jpaDevedorRepository.saveAll(devedoresEntity);
        return devedoresEntity.stream().map(d -> converter.convert(d, Devedor.class)).collect(Collectors.toList());
    }
}
