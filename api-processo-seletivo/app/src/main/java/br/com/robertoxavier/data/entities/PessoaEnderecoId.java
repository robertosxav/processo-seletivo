package br.com.robertoxavier.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PessoaEnderecoId {

    @Column(name = "pes_id",nullable = false)
    private Long pessoa;

    @Column(name = "end_id",nullable = false)
    private Long endereco;

    public Long getPessoa() {
        return pessoa;
    }

    public void setPessoa(Long pessoa) {
        this.pessoa = pessoa;
    }

    public Long getEndereco() {
        return endereco;
    }

    public void setEndereco(Long endereco) {
        this.endereco = endereco;
    }

    public PessoaEnderecoId() {
    }

    public PessoaEnderecoId(Long pessoa, Long endereco) {
        this.pessoa = pessoa;
        this.endereco = endereco;
    }
}
