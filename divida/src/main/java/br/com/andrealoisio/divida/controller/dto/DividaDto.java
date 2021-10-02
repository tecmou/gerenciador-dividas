package br.com.andrealoisio.divida.controller.dto;

import br.com.andrealoisio.divida.model.StatusDivida;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DividaDto {

    private UUID uuid;
    private BigDecimal valor;
    private LocalDate dataLimite;
    private StatusDivida status;
    private List<DevedorDto> devedores;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public StatusDivida getStatus() {
        return status;
    }

    public void setStatus(StatusDivida status) {
        this.status = status;
    }

    public List<DevedorDto> getDevedores() {
        return devedores;
    }

    public void setDevedores(List<DevedorDto> devedores) {
        this.devedores = devedores;
    }
}
