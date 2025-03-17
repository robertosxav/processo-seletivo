package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEnderecoEntity {

    @EmbeddedId
    private PessoaEnderecoId pesEndId;

    @ManyToOne
    @MapsId("pessoa")
    private PessoaEntity pessoa;

    @ManyToOne
    @MapsId("endereco")
    private EnderecoEntity endereco;

    public PessoaEnderecoId getPesEndId() {
        return pesEndId;
    }

    public void setPesEndId(PessoaEnderecoId pesEndId) {
        this.pesEndId = pesEndId;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }
}
