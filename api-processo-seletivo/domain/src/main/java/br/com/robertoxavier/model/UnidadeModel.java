package br.com.robertoxavier.model;


import java.beans.Transient;
import java.util.Set;

public class UnidadeModel {

    private Long unidId;

    private String unidNome;

    private String unidSigla;

    private Set<EnderecoModel> enderecoList;

    private Set<Long>enderecoIdList;

    public Long getUnidId() {
        return unidId;
    }

    public void setUnidId(Long unidId) {
        this.unidId = unidId;
    }

    public String getUnidNome() {
        return unidNome;
    }

    public void setUnidNome(String unidNome) {
        this.unidNome = unidNome;
    }

    public String getUnidSigla() {
        return unidSigla;
    }

    public void setUnidSigla(String unidSigla) {
        this.unidSigla = unidSigla;
    }

    public Set<Long> getEnderecoIdList() {
        return enderecoIdList;
    }

    public void setEnderecoIdList(Set<Long> enderecoIdList) {
        this.enderecoIdList = enderecoIdList;
    }

    public Set<EnderecoModel> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(Set<EnderecoModel> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public UnidadeModel() {
    }

    public UnidadeModel(Long unidId, String unidNome, String unidSigla, Set<EnderecoModel> enderecoList) {
        this.unidId = unidId;
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
        this.enderecoList = enderecoList;
    }

    public UnidadeModel(String unidNome, String unidSigla, Set<Long> enderecoIdList) {
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
        this.enderecoIdList = enderecoIdList;
    }

    public UnidadeModel(Long unidId,String unidNome, String unidSigla) {
        this.unidId = unidId;
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
    }

    public UnidadeModel(String unidNome, String unidSigla) {
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
    }

}

