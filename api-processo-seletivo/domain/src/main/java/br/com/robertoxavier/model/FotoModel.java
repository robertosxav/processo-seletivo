package br.com.robertoxavier.model;

import java.time.LocalDate;

public class FotoModel {

    private Long fpId;

    private Long pesId;

    private LocalDate fpData;

    private String fpBucket;

    private String fpHash;


    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
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

    public FotoModel() {
    }

    public FotoModel(Long fpId, Long pesId, LocalDate fpData, String fpBucket, String fpHash) {
        this.fpId = fpId;
        this.pesId = pesId;
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
    }

    public FotoModel(Long pesId, LocalDate fpData, String fpBucket, String fpHash) {
        this.pesId = pesId;
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
    }
}
