package br.com.robertoxavier.data.entities;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
public class LotacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long lotId;

    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate lotDataLotacao;

    @Column(name = "lot_data_remocao", nullable = false)
    private LocalDate lotDataRemocao;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private UnidadeEntity unidade;

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public UnidadeEntity getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeEntity unidade) {
        this.unidade = unidade;
    }

    public LocalDate getLotDataLotacao() {
        return lotDataLotacao;
    }

    public void setLotDataLotacao(LocalDate lotDataLotacao) {
        this.lotDataLotacao = lotDataLotacao;
    }

    public LocalDate getLotDataRemocao() {
        return lotDataRemocao;
    }

    public void setLotDataRemocao(LocalDate lotDataRemocao) {
        this.lotDataRemocao = lotDataRemocao;
    }

    public LotacaoEntity() {
    }

    public LotacaoEntity(Long lotId, LocalDate lotDataLotacao, LocalDate lotDataRemocao, PessoaEntity pessoa, UnidadeEntity unidade) {
        this.lotId = lotId;
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.pessoa = pessoa;
        this.unidade = unidade;
    }

    public LotacaoEntity(LocalDate lotDataLotacao, LocalDate lotDataRemocao, PessoaEntity pessoa, UnidadeEntity unidade) {
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.pessoa = pessoa;
        this.unidade = unidade;
    }
}
