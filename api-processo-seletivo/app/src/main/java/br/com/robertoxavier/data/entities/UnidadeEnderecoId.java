package br.com.robertoxavier.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UnidadeEnderecoId {

    @Column(name = "unid_id",nullable = false)
    private Long unidade;

    @Column(name = "end_id",nullable = false)
    private Long endereco;

    public Long getUnidade() {
        return unidade;
    }

    public void setUnidade(Long unidade) {
        this.unidade = unidade;
    }

    public Long getEndereco() {
        return endereco;
    }

    public void setEndereco(Long endereco) {
        this.endereco = endereco;
    }
}
