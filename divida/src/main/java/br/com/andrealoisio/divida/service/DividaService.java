package br.com.andrealoisio.divida.service;

import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.repository.DevedorRepository;
import br.com.andrealoisio.divida.repository.DividaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DividaService {

    @Autowired
    private DividaRepository dividaRepository;

    @Autowired
    private DevedorRepository devedorRepository;

    public List<Divida> list() {
        return dividaRepository.findAll();
    }

    public List<Divida> listByCpf(String cpf) {
        return dividaRepository.findByDevedores_cpf(cpf);
    }

    public Divida save(Divida divida) {
        devedorRepository.saveAll(divida.getDevedores());
        return dividaRepository.save(divida);
    }

    public void remove(UUID uuid) {
        dividaRepository.deleteById(uuid);
    }

    public Divida pagar(UUID uuid) {
        Divida divida = dividaRepository.findById(uuid);
        divida.pagar();
        return dividaRepository.save(divida);
    }
}
