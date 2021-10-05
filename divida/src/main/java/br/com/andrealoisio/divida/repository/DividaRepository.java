package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Divida;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DividaRepository {
    List<Divida> findAll();

    List<Divida> findByDevedores_cpf(String cpf);

    Divida save(Divida divida);

    void deleteById(UUID uuid);

    Optional<Divida> findById(UUID uuid);
}
