package br.com.andrealoisio.divida.controller;

import br.com.andrealoisio.divida.controller.dto.DividaDto;
import br.com.andrealoisio.divida.controller.form.DividaForm;
import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.model.DividaInvalidaException;
import br.com.andrealoisio.divida.service.DividaNotFoundException;
import br.com.andrealoisio.divida.service.DividaService;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private PojoConverter converter;

    @GetMapping
    public List<DividaDto> list() {
        List<Divida> dividas = dividaService.list();
        return converter.convert(dividas, DividaDto.class);
    }

    @GetMapping("/{cpf}")
    public List<DividaDto> list(@PathVariable String cpf) throws DividaNotFoundException {
        List<Divida> dividas = dividaService.listByCpf(cpf);
        return converter.convert(dividas, DividaDto.class);
    }

    @PostMapping
    public ResponseEntity<DividaDto> save(@RequestBody @Valid DividaForm form) throws DividaInvalidaException {
        Divida divida = form.convert();
        divida = dividaService.save(divida);
        return ResponseEntity.ok(converter.convert(divida, DividaDto.class));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> remove(@PathVariable UUID uuid) throws DividaNotFoundException {
        dividaService.remove(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DividaDto> pagar(@PathVariable UUID uuid) throws DividaNotFoundException {
        Divida divida = dividaService.pagar(uuid);
        return ResponseEntity.ok(converter.convert(divida, DividaDto.class));
    }

    @ExceptionHandler(DividaInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDividaValidacaoException(DividaInvalidaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }

    @ExceptionHandler(DividaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDividaNotFoundException(DividaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
    }
}
