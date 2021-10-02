package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.persistence.DividaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaDividaRepository extends JpaRepository<DividaEntity, UUID> {

    List<DividaEntity> findByDevedores_cpf(String cpf);
}
