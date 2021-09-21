package br.com.andrealoisio.divida.controller.dto;

import br.com.andrealoisio.divida.model.Devedor;
import br.com.andrealoisio.divida.model.Divida;

import java.util.List;
import java.util.stream.Collectors;

public class DevedorDto {
    private String cpf;
    private String nome;
    private String email;
    private String celular;

    public DevedorDto(Devedor devedor) {
        this.cpf = devedor.getCpf();
        this.nome = devedor.getNome();
        this.email = devedor.getEmail();
        this.celular = devedor.getCelular();
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public static List<DevedorDto> convert(List<Devedor> devedores) {
        return devedores.stream().map(d -> new DevedorDto(d)).collect(Collectors.toList());
    }
}
