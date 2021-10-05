package br.com.andrealoisio.divida.service;

import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.repository.DevedorRepository;
import br.com.andrealoisio.divida.repository.DividaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<Divida> listByCpf(String cpf) throws DividaNotFoundException {
        List<Divida> dividas = dividaRepository.findByDevedores_cpf(cpf);
        if (dividas.isEmpty()) {
            throw new DividaNotFoundException("exception.divida.notfound");
        }
        return dividas;
    }

    public Divida save(Divida divida) {
        devedorRepository.saveAll(divida.getDevedores());
        return dividaRepository.save(divida);
    }

    public void remove(UUID uuid) throws DividaNotFoundException {
        Optional<Divida> optionalDivida = dividaRepository.findById(uuid);
        if(optionalDivida.isEmpty()) {
            throw new DividaNotFoundException("exception.divida.notfound");
        }
        dividaRepository.deleteById(uuid);
    }

    public Divida pagar(UUID uuid) throws DividaNotFoundException {
        Optional<Divida> optionalDivida = dividaRepository.findById(uuid);
        if(optionalDivida.isEmpty()) {
            throw new DividaNotFoundException("exception.divida.notfound");
        }
        Divida divida = optionalDivida.get();
        divida.pagar();
        return dividaRepository.save(divida);
    }
}
