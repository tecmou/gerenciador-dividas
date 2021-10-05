package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.persistence.DividaEntity;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DividaRepositoryImplForJpa implements DividaRepository {

    @Autowired
    private JpaDividaRepository jpaDividaRepository;

    @Autowired
    private PojoConverter converter;

    @Override
    public Optional<Divida> findById(UUID uuid) {
        Optional<DividaEntity> optionalDividaEntity = jpaDividaRepository.findById(uuid);
        return converter.convert(optionalDividaEntity, Divida.class);
    }

    @Override
    public List<Divida> findAll() {
        List<DividaEntity> dividasEntity = jpaDividaRepository.findAll();
        return converter.convert(dividasEntity, Divida.class);
    }

    @Override
    public List<Divida> findByDevedores_cpf(String cpf) {
        List<DividaEntity> dividasEntity = jpaDividaRepository.findByDevedores_cpf(cpf);
        return converter.convert(dividasEntity, Divida.class);
    }

    @Override
    public Divida save(Divida divida) {
        DividaEntity dividaEntity = converter.convert(divida, DividaEntity.class);
        dividaEntity = jpaDividaRepository.save(dividaEntity);
        return converter.convert(dividaEntity, Divida.class);
    }

    @Override
    public void deleteById(UUID uuid) {
        jpaDividaRepository.deleteById(uuid);
    }
}
