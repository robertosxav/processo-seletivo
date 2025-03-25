package br.com.robertoxavier.data.entities.vo;

import java.time.LocalDate;
import java.time.Period;

public class ServidoresUnidadeVo {

    private String nome;

    private LocalDate dataNascimento;

    private String nomeUnidade;

    private String listaLinkFotos;

    public ServidoresUnidadeVo() {
    }

    public ServidoresUnidadeVo(String nome, LocalDate dataNascimento, String nomeUnidade, String listaLinkFotos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nomeUnidade = nomeUnidade;
        this.listaLinkFotos = listaLinkFotos;
    }

    public ServidoresUnidadeVo(String nome, String nomeUnidade) {
        this.nome = nome;
        this.nomeUnidade = nomeUnidade;
    }

    public ServidoresUnidadeVo(String nome, String nomeUnidade,LocalDate dataNascimento) {
        this.nome = nome;
        this.nomeUnidade = nomeUnidade;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getListaLinkFotos() {
        return listaLinkFotos;
    }

    public void setListaLinkFotos(String listaLinkFotos) {
        this.listaLinkFotos = listaLinkFotos;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
