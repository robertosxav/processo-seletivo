package br.com.robertoxavier.model;

import java.time.LocalDate;


public class ServidorTemporarioModel {

    private Long id;

    private LocalDate stDataAdmissao;

    private LocalDate stDataDemissao;

    private PessoaModel pessoa;

    public PessoaModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getStDataAdmissao() {
        return stDataAdmissao;
    }

    public void setStDataAdmissao(LocalDate stDataAdmissao) {
        this.stDataAdmissao = stDataAdmissao;
    }

    public LocalDate getStDataDemissao() {
        return stDataDemissao;
    }

    public void setStDataDemissao(LocalDate stDataDemissao) {
        this.stDataDemissao = stDataDemissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServidorTemporarioModel() {
    }

    public ServidorTemporarioModel(Long id, LocalDate stDataAdmissao, LocalDate stDataDemissao, PessoaModel pessoa) {
        this.id = id;
        this.stDataAdmissao = stDataAdmissao;
        this.stDataDemissao = stDataDemissao;
        this.pessoa = pessoa;
    }

    public ServidorTemporarioModel(LocalDate stDataAdmissao, LocalDate stDataDemissao, PessoaModel pessoa) {
        this.stDataAdmissao = stDataAdmissao;
        this.stDataDemissao = stDataDemissao;
        this.pessoa = pessoa;
    }
}
