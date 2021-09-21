package br.com.andrealoisio.divida.controller;

import br.com.andrealoisio.divida.controller.dto.DividaDto;
import br.com.andrealoisio.divida.controller.form.DividaForm;
import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.service.DividaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dividas")
public class DividaController {

    @Autowired
    private DividaService dividaService;

    @GetMapping
    public List<DividaDto> list() {
        List<Divida> dividas = dividaService.list();
        return DividaDto.convert(dividas);
    }

    @GetMapping("/{cpf}")
    public List<DividaDto> list(@PathVariable String cpf) {
        List<Divida> dividas = dividaService.listByCpf(cpf);
        return DividaDto.convert(dividas);
    }

    @PostMapping
    public ResponseEntity<DividaDto> save(@RequestBody @Valid DividaForm form) {
        Divida divida = form.convert();
        dividaService.save(divida);
        return ResponseEntity.ok(new DividaDto(divida));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> remove(@PathVariable UUID uuid) {
        dividaService.remove(uuid);
        return ResponseEntity.ok().build();
    }
}
