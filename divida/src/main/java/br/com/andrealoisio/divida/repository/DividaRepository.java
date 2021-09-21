package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DividaRepository extends JpaRepository<Divida, UUID> {

    List<Divida> findByDevedores_cpf(String cpf);
}
