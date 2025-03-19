package br.com.robertoxavier.model;


public class ServidorEfetivoModel {

    private Long id;

    private String seMatricula;

    private PessoaModel pessoa;

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
}
