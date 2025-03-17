package br.com.robertoxavier.model;

public class EnderecoModel {

    private Long endId;

    private String endTipoLogradouro;

    private String endLogradouro;

    private Integer endNumero;

    private String endBairro;

    private CidadeModel cidade;

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }

    public void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public Integer getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(Integer endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public CidadeModel getCidade() {
        return cidade;
    }

    public void setCidade(CidadeModel cidade) {
        this.cidade = cidade;
    }

    public EnderecoModel() {
    }

    public EnderecoModel(Long endId, String endTipoLogradouro, String endLogradouro, Integer endNumero, String endBairro, CidadeModel cidade) {
        this.endId = endId;
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.cidade = cidade;
    }

    public EnderecoModel(Long endId, String endTipoLogradouro, String endLogradouro, Integer endNumero, String endBairro) {
        this.endId = endId;
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
    }

    public EnderecoModel(String endTipoLogradouro, String endLogradouro, Integer endNumero, String endBairro) {
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
    }
}
