package br.com.robertoxavier.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Long endId;

    @Column(name = "end_tipo_logradouro", length = 50, nullable = false)
    private String endTipoLogradouro;

    @Column(name = "end_logradouro", length = 200, nullable = false)
    private String endLogradouro;

    @Column(name = "end_numero", length = 200, nullable = false)
    private Integer endNumero;

    @Column(name = "end_bairro", length = 100, nullable = false)
    private String endBairro;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private CidadeEntity cidade;

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }

    public void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public Integer getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(Integer endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public CidadeEntity getCidade() {
        return cidade;
    }

    public void setCidade(CidadeEntity cidade) {
        this.cidade = cidade;
    }

    public EnderecoEntity() {
    }

    public EnderecoEntity(Long endId, String endTipoLogradouro, String endLogradouro, Integer endNumero, String endBairro, CidadeEntity cidade) {
        this.endId = endId;
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.cidade = cidade;
    }

    public EnderecoEntity(String endTipoLogradouro, String endLogradouro, Integer endNumero,
                          String endBairro/*, CidadeEntity cidade*/) {
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
       // this.cidade = cidade;
    }
}
