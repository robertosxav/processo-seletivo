package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "cidade_seq")
    @SequenceGenerator( name = "cidade_seq", sequenceName = "cidade_cid_id_seq", allocationSize = 1)
    @Column(name = "cid_id")
    private Long cidId;

    @Column(name = "cid_nome", length = 200, nullable = false)
    private String cidNome;

    @Column(name = "cid_uf", length = 2, nullable = false)
    private String cidUf;

    public Long getCidId() {
        return cidId;
    }

    public void setCidId(Long cidId) {
        this.cidId = cidId;
    }

    public String getCidNome() {
        return cidNome;
    }

    public void setCidNome(String cidNome) {
        this.cidNome = cidNome;
    }

    public String getCidUf() {
        return cidUf;
    }

    public void setCidUf(String cidUf) {
        this.cidUf = cidUf;
    }

    public CidadeEntity() {
    }

    public CidadeEntity(Long cidId, String cidNome, String cidUf) {
        this.cidId = cidId;
        this.cidNome = cidNome;
        this.cidUf = cidUf;
    }

    public CidadeEntity(String cidNome, String cidUf) {
        this.cidNome = cidNome;
        this.cidUf = cidUf;
    }
}
