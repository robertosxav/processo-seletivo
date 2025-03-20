package br.com.robertoxavier.model;

import java.time.LocalDate;

public class FotoModel {

    private Long fpId;

    private PessoaModel pessoa;

    private LocalDate fpData;

    private String fpBucket;

    private String fpHash;

    public PessoaModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getFpData() {
        return fpData;
    }

    public void setFpData(LocalDate fpData) {
        this.fpData = fpData;
    }

    public String getFpBucket() {
        return fpBucket;
    }

    public void setFpBucket(String fpBucket) {
        this.fpBucket = fpBucket;
    }

    public String getFpHash() {
        return fpHash;
    }

    public void setFpHash(String fpHash) {
        this.fpHash = fpHash;
    }

    public Long getFpId() {
        return fpId;
    }

    public void setFpId(Long fpId) {
        this.fpId = fpId;
    }


}
