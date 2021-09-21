package br.com.andrealoisio.divida.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devedores")
public class Devedor {

    @Id
    private String cpf;
    private String nome;
    private String email;
    private String celular;
    @ManyToMany(mappedBy = "devedores", fetch = FetchType.LAZY)
    private List<Divida> dividas;

    public Devedor() {
        dividas = new ArrayList<>();
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

    public List<Divida> getDividas() {
        return dividas;
    }

    public void setDividas(List<Divida> dividas) {
        this.dividas = dividas;
    }
}
