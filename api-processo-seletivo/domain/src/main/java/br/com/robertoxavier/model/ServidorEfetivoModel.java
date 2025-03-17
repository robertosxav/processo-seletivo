package br.com.robertoxavier.model;


public class ServidorEfetivoModel {

    private PessoaModel pessoa;

    private String seMatricula;


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
}
