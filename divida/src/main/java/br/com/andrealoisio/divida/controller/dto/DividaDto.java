package br.com.andrealoisio.divida.controller.dto;

import br.com.andrealoisio.divida.model.Devedor;
import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.model.StatusDivida;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DividaDto {

    private UUID uuid;
    private BigDecimal valor;
    private LocalDate dataLimite;
    private StatusDivida status;
    private List<DevedorDto> devedores;

    public DividaDto(Divida divida) {
        this.uuid = divida.getUuid();
        this.valor = divida.getValor();
        this.dataLimite = divida.getDataLimite();
        this.status = divida.getStatus();
        this.devedores = DevedorDto.convert(divida.getDevedores());
    }

    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public StatusDivida getStatus() {
        return status;
    }

    public List<DevedorDto> getDevedores() {
        return devedores;
    }

    public static List<DividaDto> convert(List<Divida> dividas) {
        return dividas.stream().map(d -> new DividaDto(d)).collect(Collectors.toList());
    }
}
