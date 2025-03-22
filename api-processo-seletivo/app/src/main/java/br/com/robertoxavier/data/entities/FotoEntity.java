package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
public class FotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "foto_pessoa_seq")
    @SequenceGenerator( name = "foto_pessoa_seq", sequenceName = "foto_pessoa_fp_id_seq", allocationSize = 1)
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

    @Transient
    private Long pesId;

    @Transient
    private String linkFoto;

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

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public FotoEntity() {
    }

    public FotoEntity(Long fpId, PessoaEntity pessoa, LocalDate fpData, String fpBucket, String fpHash, Long pesId, String linkFoto) {
        this.fpId = fpId;
        this.pessoa = pessoa;
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
        this.pesId = pesId;
        this.linkFoto = linkFoto;
    }

    public FotoEntity(LocalDate fpData, String fpBucket, String fpHash, Long pesId, String linkFoto) {
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
        this.pesId = pesId;
        this.linkFoto = linkFoto;
    }

    public FotoEntity(LocalDate fpData, String fpBucket, String fpHash) {
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
    }
}
