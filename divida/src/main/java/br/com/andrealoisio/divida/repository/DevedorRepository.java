package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Devedor;
import br.com.andrealoisio.divida.model.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevedorRepository extends JpaRepository<Devedor, String> {

}
