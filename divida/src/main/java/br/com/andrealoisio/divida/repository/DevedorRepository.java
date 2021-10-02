package br.com.andrealoisio.divida.repository;

import br.com.andrealoisio.divida.model.Devedor;

import java.util.List;

public interface DevedorRepository {

    List<Devedor> saveAll(List<Devedor> devedores);
}
