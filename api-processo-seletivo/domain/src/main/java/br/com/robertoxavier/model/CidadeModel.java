package br.com.robertoxavier.model;

public class CidadeModel {

    private Long cidId;

    private String cidNome;

    private String cidUf;

    public Long getCidId() {
        return cidId;
    }

    public void setCidId(Long cidId) {
        this.cidId = cidId;
    }

    public String getCidNome() {
        return cidNome;
    }

    public void setCidNome(String cidNome) {
        this.cidNome = cidNome;
    }

    public String getCidUf() {
        return cidUf;
    }

    public void setCidUf(String cidUf) {
        this.cidUf = cidUf;
    }

    public CidadeModel() {
    }

    public CidadeModel(Long cidId, String cidNome, String cidUf) {
        this.cidId = cidId;
        this.cidNome = cidNome;
        this.cidUf = cidUf;
    }

    public CidadeModel(String cidNome, String cidUf) {
        this.cidNome = cidNome;
        this.cidUf = cidUf;
    }
}
