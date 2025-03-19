package br.com.robertoxavier.model;

import java.time.LocalDate;
import java.util.Set;


public class PessoaModel {

    private Long pesId;

    private String pesNome;

    private LocalDate pesDataNascimento;

    private Set<EnderecoModel> enderecoList;

    private String pesSexo;

    private String pesMae;

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

    public Set<EnderecoModel> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(Set<EnderecoModel> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public PessoaModel() {

    }

    public PessoaModel(Long pesId, String pesNome, LocalDate pesDataNascimento, Set<EnderecoModel> enderecoList, String pesSexo, String pesMae, String pesPai) {
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.enderecoList = enderecoList;
        this.pesSexo = pesSexo;
        this.pesMae = pesMae;
        this.pesPai = pesPai;
    }

    public PessoaModel(String pesNome, LocalDate pesDataNascimento, Set<EnderecoModel> enderecoList, String pesSexo, String pesMae, String pesPai) {
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.enderecoList = enderecoList;
        this.pesSexo = pesSexo;
        this.pesMae = pesMae;
        this.pesPai = pesPai;
    }

    public PessoaModel(String pesNome, LocalDate pesDataNascimento, String pesSexo, String pesMae, String pesPai) {
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pesSexo = pesSexo;
        this.pesMae = pesMae;
        this.pesPai = pesPai;
    }
}
