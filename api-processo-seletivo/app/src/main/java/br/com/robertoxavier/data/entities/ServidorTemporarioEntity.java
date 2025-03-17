package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporarioEntity {

    @Id
    @ManyToOne
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
}
