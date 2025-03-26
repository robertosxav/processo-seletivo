package br.com.robertoxavier.model;


import java.util.Set;

public class ServidorEfetivoModel {

    private Long id;

    private String seMatricula;

    private PessoaModel pessoa;

    private String nome;

    private Long idade;

    private String nomeUnidade;

    private Set<String> listLinkFotos;

    public PessoaModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }

    public String getSeMatricula() {
        return seMatricula;
    }

    public void setSeMatricula(String seMatricula) {
        this.seMatricula = seMatricula;
    }

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

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public Set<String> getListLinkFotos() {
        return listLinkFotos;
    }

    public void setListLinkFotos(Set<String> listLinkFotos) {
        this.listLinkFotos = listLinkFotos;
    }

    public ServidorEfetivoModel() {
    }
    public ServidorEfetivoModel(Long id, String seMatricula, PessoaModel pessoa) {
        this.id = id;
        this.seMatricula = seMatricula;
        this.pessoa = pessoa;
    }

    public ServidorEfetivoModel(String seMatricula, PessoaModel pessoa) {
        this.seMatricula = seMatricula;
        this.pessoa = pessoa;
    }

    public ServidorEfetivoModel(String nome, Long idade, String nomeUnidade, Set<String>listLinkFotos) {
        this.nome = nome;
        this.idade = idade;
        this.nomeUnidade = nomeUnidade;
        this.listLinkFotos = listLinkFotos;
    }
}
