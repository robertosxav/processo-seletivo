package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivoEntity {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @Column(name = "se_matricula")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServidorEfetivoEntity() {
    }

    public ServidorEfetivoEntity(Long id, String seMatricula, PessoaEntity pessoa) {
        this.id = id;
        this.seMatricula = seMatricula;
        this.pessoa = pessoa;
    }
}
