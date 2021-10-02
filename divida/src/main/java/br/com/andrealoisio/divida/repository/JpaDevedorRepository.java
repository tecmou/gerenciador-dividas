package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.persistence.DevedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDevedorRepository extends JpaRepository<DevedorEntity, String> {

}
