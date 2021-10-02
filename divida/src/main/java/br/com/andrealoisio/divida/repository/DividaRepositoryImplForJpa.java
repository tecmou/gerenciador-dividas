package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.persistence.DividaEntity;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DividaRepositoryImplForJpa implements DividaRepository {

    @Autowired
    private JpaDividaRepository jpaDividaRepository;

    @Autowired
    private PojoConverter converter;

    @Override
    public Divida findById(UUID uuid) {
        DividaEntity dividaEntity = jpaDividaRepository.findById(uuid).get();
        return converter.convert(dividaEntity, Divida.class);
    }

    @Override
    public List<Divida> findAll() {
        List<DividaEntity> dividasEntity = jpaDividaRepository.findAll();
        return dividasEntity.stream().map(d -> converter.convert(d, Divida.class)).collect(Collectors.toList());
    }

    @Override
    public List<Divida> findByDevedores_cpf(String cpf) {
        List<DividaEntity> dividasEntity = jpaDividaRepository.findByDevedores_cpf(cpf);
        return dividasEntity.stream().map(d -> converter.convert(d, Divida.class)).collect(Collectors.toList());
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
