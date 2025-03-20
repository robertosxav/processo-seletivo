package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEnderecoEntity {

    @EmbeddedId
    private PessoaEnderecoId pesEndId;

    @ManyToOne
    @MapsId("pessoa")
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;


    @ManyToOne
    @MapsId("endereco")
    @JoinColumn(name = "end_id")
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

    public PessoaEnderecoEntity() {
    }

    public PessoaEnderecoEntity(PessoaEnderecoId pesEndId) {
        this.pesEndId = pesEndId;
    }

    public PessoaEnderecoEntity(PessoaEntity pessoa, EnderecoEntity endereco) {
        this.pessoa = pessoa;
        this.endereco = endereco;
    }

    public PessoaEnderecoEntity(PessoaEnderecoId pesEndId, PessoaEntity pessoa, EnderecoEntity endereco) {
        this.pesEndId = pesEndId;
        this.pessoa = pessoa;
        this.endereco = endereco;
    }
}
