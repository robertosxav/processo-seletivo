package br.com.robertoxavier.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "pessoa")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long pesId;

    @Column(name = "pes_nome", length = 200, nullable = false)
    private String pesNome;

    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate pesDataNascimento;

    @Column(name = "pes_sexo", length = 9, nullable = false)
    private String pesSexo;

    @Column(name = "pes_mae", length = 200, nullable = false)
    private String pesMae;

    @Column(name = "pes_pai", length = 200, nullable = false)
    private String pesPai;

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public LocalDate getPesDataNascimento() {
        return pesDataNascimento;
    }

    public void setPesDataNascimento(LocalDate pesDataNascimento) {
        this.pesDataNascimento = pesDataNascimento;
    }

    public String getPesSexo() {
        return pesSexo;
    }

    public void setPesSexo(String pesSexo) {
        this.pesSexo = pesSexo;
    }

    public String getPesMae() {
        return pesMae;
    }

    public void setPesMae(String pesMae) {
        this.pesMae = pesMae;
    }

    public String getPesPai() {
        return pesPai;
    }

    public void setPesPai(String pesPai) {
        this.pesPai = pesPai;
    }
}
