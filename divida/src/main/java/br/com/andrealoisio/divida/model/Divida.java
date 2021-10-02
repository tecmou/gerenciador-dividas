package br.com.andrealoisio.divida.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Divida {

    private UUID uuid;
    private BigDecimal valor;
    private LocalDate dataLimite;
    private StatusDivida status;
    private List<Devedor> devedores;

    public Divida() {
        this.devedores = new ArrayList<>();
    }

    public Divida(BigDecimal valor, LocalDate dataLimite, List<Devedor> devedores) {
        iniciarValor(valor);
        iniciarDataLimite(dataLimite);
        iniciarDevedores(devedores);
        this.status = StatusDivida.NAO_PAGA;
    }

    protected void iniciarValor(BigDecimal valor) {
        if(!isValorMaiorQue100(valor)) {
            throw new IllegalArgumentException("Valor da divida deve ser maior que 100 reais");
        }
        this.valor = valor;
    }

    private boolean isValorMaiorQue100(BigDecimal valor) {
        return new BigDecimal(100).compareTo(valor) < 0;
    }

    protected void iniciarDataLimite(LocalDate dataLimite) {
        if(!isDataLimiteUmaDataFuturaQueNaoSuperaUmAno(dataLimite)) {
            throw new IllegalArgumentException("Data limite precisa ser uma data futura cujo prazo não supere 1 ano");
        }
        this.dataLimite = dataLimite;
    }

    private boolean isDataLimiteUmaDataFuturaQueNaoSuperaUmAno(LocalDate dataLimite) {
        LocalDate hoje = LocalDate.now();
        LocalDate umAnoAPartirDeHoje = hoje.plusYears(1);

        return hoje.isBefore(dataLimite) && !umAnoAPartirDeHoje.isBefore(dataLimite);
    }

    protected void iniciarDevedores(List<Devedor> devedores) {
        if(devedores.isEmpty()) {
            throw new IllegalArgumentException("A divida precisa ter um devedor");
        }
        this.devedores = devedores;
    }

    public void pagar() {
        this.status = StatusDivida.PAGA;
    }

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

    public List<Devedor> getDevedores() {
        return devedores;
    }

    public void setDevedores(List<Devedor> devedores) {
        this.devedores = devedores;
    }
}
