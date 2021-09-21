package br.com.andrealoisio.divida.controller.form;

import br.com.andrealoisio.divida.controller.dto.DevedorDto;
import br.com.andrealoisio.divida.model.Devedor;
import br.com.andrealoisio.divida.model.Divida;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class DevedorForm {

    @NotNull@ NotEmpty @CPF
    private String cpf;
    @NotNull @NotEmpty @Length(max = 49)
    private String nome;
    @NotNull @NotEmpty @Email
    private String email;
    private String celular;

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

    public Devedor convert() {
        Devedor devedor = new Devedor();
        devedor.setCpf(this.cpf);
        devedor.setNome(this.nome);
        devedor.setEmail(this.email);
        devedor.setCelular(this.celular);
        return devedor;
    }

    public static List<Devedor> convert(List<DevedorForm> devedores) {
        return devedores.stream().map(d -> {
            Devedor devedor = new Devedor();
            devedor.setCpf(d.getCpf());
            devedor.setNome(d.getNome());
            devedor.setEmail(d.getEmail());
            devedor.setCelular(d.getCelular());
            return devedor;
        }).collect(Collectors.toList());
    }
}
