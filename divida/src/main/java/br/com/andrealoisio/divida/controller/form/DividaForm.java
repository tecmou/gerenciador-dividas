package br.com.andrealoisio.divida.controller.form;

import br.com.andrealoisio.divida.model.Devedor;
import br.com.andrealoisio.divida.model.Divida;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DividaForm {

    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate dataLimite;
    @Valid
    private List<DevedorForm> devedores;

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

    public List<DevedorForm> getDevedores() {
        return devedores;
    }

    public void setDevedores(List<DevedorForm> devedores) {
        this.devedores = devedores;
    }

    public Divida convert() {
        Divida divida = new Divida();
        divida.setValor(this.valor);
        divida.setDataLimite(this.dataLimite);
        divida.setDevedores(DevedorForm.convert(this.devedores));
        return divida;
    }
}