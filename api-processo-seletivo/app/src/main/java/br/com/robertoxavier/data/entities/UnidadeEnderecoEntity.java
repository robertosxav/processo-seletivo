package br.com.robertoxavier.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "unidade_endereco")
public class UnidadeEnderecoEntity {

    @EmbeddedId
    private UnidadeEnderecoId unidEndId;

    @ManyToOne
    @MapsId("unidade")
    private UnidadeEntity unidade;


    @ManyToOne
    @MapsId("endereco")
    private EnderecoEntity endereco;

    public UnidadeEnderecoId getUnidEndId() {
        return unidEndId;
    }

    public void setUnidEndId(UnidadeEnderecoId unidEndId) {
        this.unidEndId = unidEndId;
    }

    public UnidadeEntity getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeEntity unidade) {
        this.unidade = unidade;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }
}
