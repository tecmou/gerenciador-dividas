package br.com.andrealoisio.divida.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dividas")
public class Divida {

    @Id
    @GeneratedValue
    private UUID uuid;
    private BigDecimal valor;
    private LocalDate dataLimite;
    private StatusDivida status;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="dividas_devedores",
            joinColumns=
                @JoinColumn(name="fk_divida", referencedColumnName="uuid"),
            inverseJoinColumns=
                @JoinColumn(name="fk_devedor", referencedColumnName="cpf")
    )
    private List<Devedor> devedores;

    public Divida() {
        this.status = StatusDivida.NAO_PAGA;
        this.devedores = new ArrayList<>();
    }

    public void setValor(BigDecimal valor) {
        if(!isValorMaiorQue100(valor)) {
            throw new IllegalArgumentException("Valor da divida deve ser maior que 100 reais");
        }
        this.valor = valor;
    }

    private boolean isValorMaiorQue100(BigDecimal valor) {
        return new BigDecimal(100).compareTo(valor) < 0;
    }

    public void setDataLimite(LocalDate dataLimite) {
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

    public void pagar() {
        this.status = StatusDivida.PAGA;
    }

    public void setDevedores(List<Devedor> devedores) {
        this.devedores = devedores;
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

    public List<Devedor> getDevedores() {
        return devedores;
    }
}
