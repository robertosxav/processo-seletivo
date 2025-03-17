package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivoEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @Column(name = "st_matricula")
    private String seMatricula;


    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public String getSeMatricula() {
        return seMatricula;
    }

    public void setSeMatricula(String seMatricula) {
        this.seMatricula = seMatricula;
    }
}
