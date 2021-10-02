package br.com.andrealoisio.divida.controller;

import br.com.andrealoisio.divida.controller.dto.DividaDto;
import br.com.andrealoisio.divida.controller.form.DividaForm;
import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.service.DividaService;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dividas")
public class DividaController {

    @Autowired
    private DividaService dividaService;

    @Autowired
    private PojoConverter converter;

    @GetMapping
    public List<DividaDto> list() {
        List<Divida> dividas = dividaService.list();
        return dividas.stream().map(d -> converter.convert(d, DividaDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{cpf}")
    public List<DividaDto> list(@PathVariable String cpf) {
        List<Divida> dividas = dividaService.listByCpf(cpf);
        return dividas.stream().map(d -> converter.convert(d, DividaDto.class)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<DividaDto> save(@RequestBody @Valid DividaForm form) {
        Divida divida = form.convert();
        divida = dividaService.save(divida);
        return ResponseEntity.ok(converter.convert(divida, DividaDto.class));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> remove(@PathVariable UUID uuid) {
        dividaService.remove(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DividaDto> pagar(@PathVariable UUID uuid) {
        Divida divida = dividaService.pagar(uuid);
        return ResponseEntity.ok(converter.convert(divida, DividaDto.class));
    }
}
