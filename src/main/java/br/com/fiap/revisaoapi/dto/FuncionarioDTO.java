package br.com.fiap.revisaoapi.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> {

    private Long id;
    private String nome;
    private String email;
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
