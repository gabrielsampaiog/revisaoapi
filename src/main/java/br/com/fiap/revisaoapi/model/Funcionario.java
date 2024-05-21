package br.com.fiap.revisaoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do funcionário é obrigatório")
    @Size(min = 10, max=100, message = "O nome deve ter entre 10 e 100 caracteres")
    @Column(name = "ds_nome")
    private String nome;


    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})$", message = "O CPF deve estar no formato 000.000.000-00")
    @NotBlank(message = "O CPF do funcionário é obrigatório")
    @Size(min = 14, max = 14, message = "O CPF deve ter 14 caracteres")
    @Column(name = "ds_cpf")
    private String cpf;


    @NotBlank(message = "O email do funcionário é obrigatório")
    @Email(message = "Email inválido")
    @Column(name = "ds_email")
    private String email;


    @NotNull(message = "A data de admissão do funcionário é obrigatória")
    @Column(name = "dt_adm")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dataAdmissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

}
