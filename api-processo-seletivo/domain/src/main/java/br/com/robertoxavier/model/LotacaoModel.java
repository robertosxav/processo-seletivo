package br.com.robertoxavier.model;

import java.time.LocalDate;

public class LotacaoModel {

    private Long lotId;

    private LocalDate lotDataLotacao;

    private LocalDate lotDataRemocao;

    private Long pesId;

    private Long unidId;

    private PessoaModel pessoaModel;

    private UnidadeModel unidadeModel;

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
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

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public Long getUnidId() {
        return unidId;
    }

    public void setUnidId(Long unidId) {
        this.unidId = unidId;
    }

    public LotacaoModel() {
    }

    public PessoaModel getPessoaModel() {
        return pessoaModel;
    }

    public void setPessoaModel(PessoaModel pessoaModel) {
        this.pessoaModel = pessoaModel;
    }

    public UnidadeModel getUnidadeModel() {
        return unidadeModel;
    }

    public void setUnidadeModel(UnidadeModel unidadeModel) {
        this.unidadeModel = unidadeModel;
    }

    public LotacaoModel(Long lotId, LocalDate lotDataLotacao, LocalDate lotDataRemocao, Long pesId, Long unidId) {
        this.lotId = lotId;
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.pesId = pesId;
        this.unidId = unidId;
    }

    public LotacaoModel(LocalDate lotDataLotacao, LocalDate lotDataRemocao, Long pesId, Long unidId) {
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.pesId = pesId;
        this.unidId = unidId;
    }

    public LotacaoModel(Long lotId, LocalDate lotDataLotacao, LocalDate lotDataRemocao, PessoaModel pessoaModel, UnidadeModel unidadeModel) {
        this.lotId = lotId;
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.pessoaModel = pessoaModel;
        this.unidadeModel = unidadeModel;
    }

    public LotacaoModel(LocalDate lotDataLotacao, LocalDate lotDataRemocao, PessoaModel pessoaModel, UnidadeModel unidadeModel) {

        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.pessoaModel = pessoaModel;
        this.unidadeModel = unidadeModel;
    }

}
