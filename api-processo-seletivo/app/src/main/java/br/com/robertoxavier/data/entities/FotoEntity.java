package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

public class FotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long fpId;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @Column(name = "fp_data", nullable = false)
    private LocalDate fpData;

    @Column(name = "fp_bucket", length = 50, nullable = false)
    private String fpBucket;

    @Column(name = "fp_hash", length = 50, nullable = false)
    private String fpHash;

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
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
