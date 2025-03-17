package br.com.robertoxavier.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "unidade")
public class UnidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "unidade_seq")
    @SequenceGenerator( name = "unidade_seq", sequenceName = "unidade_unid_id_seq", allocationSize = 1)
    @Column(name = "unid_id")
    private Long unidId;

    @Column(name = "unid_nome", length = 200, nullable = false)
    private String unidNome;

    @Column(name = "unid_sigla", length = 20, nullable = false)
    private String unidSigla;

    public Long getUnidId() {
        return unidId;
    }

    public void setUnidId(Long unidId) {
        this.unidId = unidId;
    }

    public String getUnidNome() {
        return unidNome;
    }

    public void setUnidNome(String unidNome) {
        this.unidNome = unidNome;
    }

    public String getUnidSigla() {
        return unidSigla;
    }

    public void setUnidSigla(String unidSigla) {
        this.unidSigla = unidSigla;
    }

    public UnidadeEntity() {
    }

    public UnidadeEntity(Long unidId, String unidNome, String unidSigla) {
        this.unidId = unidId;
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
    }

    public UnidadeEntity(String unidNome, String unidSigla) {
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
    }
}

