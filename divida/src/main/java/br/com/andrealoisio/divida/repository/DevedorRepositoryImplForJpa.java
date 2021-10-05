package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Devedor;
import br.com.andrealoisio.divida.persistence.DevedorEntity;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DevedorRepositoryImplForJpa implements DevedorRepository {

    @Autowired
    private JpaDevedorRepository jpaDevedorRepository;

    @Autowired
    private PojoConverter converter;

    @Override
    public List<Devedor> saveAll(List<Devedor> devedores) {
        List<DevedorEntity> devedoresEntity = converter.convert(devedores, DevedorEntity.class);
        devedoresEntity = jpaDevedorRepository.saveAll(devedoresEntity);
        return converter.convert(devedoresEntity, Devedor.class);
    }
}
