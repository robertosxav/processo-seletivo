package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporarioEntity {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @Column(name = "st_data_admissao")
    private LocalDate stDataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate stDataDemissao;

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getStDataAdmissao() {
        return stDataAdmissao;
    }

    public void setStDataAdmissao(LocalDate stDataAdmissao) {
        this.stDataAdmissao = stDataAdmissao;
    }

    public LocalDate getStDataDemissao() {
        return stDataDemissao;
    }

    public void setStDataDemissao(LocalDate stDataDemissao) {
        this.stDataDemissao = stDataDemissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServidorTemporarioEntity() {
    }

    public ServidorTemporarioEntity(Long id, LocalDate stDataAdmissao, LocalDate stDataDemissao, PessoaEntity pessoa) {
        this.id = id;
        this.stDataAdmissao = stDataAdmissao;
        this.stDataDemissao = stDataDemissao;
        this.pessoa = pessoa;
    }

    public ServidorTemporarioEntity(LocalDate stDataAdmissao, LocalDate stDataDemissao, PessoaEntity pessoa) {
        this.stDataAdmissao = stDataAdmissao;
        this.stDataDemissao = stDataDemissao;
        this.pessoa = pessoa;
    }
}
