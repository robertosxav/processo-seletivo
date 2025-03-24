package br.com.robertoxavier.data.entities.vo;

public class ServidoresUnidadeVo {

    private String nome;

    private Long idade;

    private String nomeUnidade;

    private String listaLinkFotos;

    public ServidoresUnidadeVo() {
    }

    public ServidoresUnidadeVo(String nome, Long idade, String nomeUnidade, String listaLinkFotos) {
        this.nome = nome;
        this.idade = idade;
        this.nomeUnidade = nomeUnidade;
        this.listaLinkFotos = listaLinkFotos;
    }

    public ServidoresUnidadeVo(String nome, String nomeUnidade) {
        this.nome = nome;
        this.nomeUnidade = nomeUnidade;
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

    public String getListaLinkFotos() {
        return listaLinkFotos;
    }

    public void setListaLinkFotos(String listaLinkFotos) {
        this.listaLinkFotos = listaLinkFotos;
    }
}
