package br.com.andrealoisio.divida.persistence;

import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.model.StatusDivida;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "dividas")
public class DividaEntity {

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
    private List<DevedorEntity> devedores;

    public DividaEntity() {
    }

    public DividaEntity(Divida divida) {
        this.uuid = divida.getUuid();
        this.valor = divida.getValor();
        this.dataLimite = divida.getDataLimite();
        this.status = divida.getStatus();
        this.devedores = divida.getDevedores().stream().map(d -> new DevedorEntity(d)).collect(Collectors.toList());
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

    public List<DevedorEntity> getDevedores() {
        return devedores;
    }

    public void setDevedores(List<DevedorEntity> devedores) {
        this.devedores = devedores;
    }
}
