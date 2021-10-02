package br.com.andrealoisio.divida.persistence;

import br.com.andrealoisio.divida.model.Devedor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "devedores")
public class DevedorEntity {

    @Id
    private String cpf;
    private String nome;
    private String email;
    private String celular;
    @ManyToMany(mappedBy = "devedores", fetch = FetchType.LAZY)
    private List<DividaEntity> dividas;

    public DevedorEntity(){
    }

    public DevedorEntity(Devedor devedor) {
        this.cpf = devedor.getCpf();
        this.nome = devedor.getNome();
        this.email = devedor.getEmail();
        this.celular = devedor.getCelular();
        this.dividas = devedor.getDividas().stream().map(d -> new DividaEntity(d)).collect(Collectors.toList());
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<DividaEntity> getDividas() {
        return dividas;
    }

    public void setDividas(List<DividaEntity> dividas) {
        this.dividas = dividas;
    }
}
