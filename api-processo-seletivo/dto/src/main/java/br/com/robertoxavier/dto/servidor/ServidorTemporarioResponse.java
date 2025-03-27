package br.com.robertoxavier.dto.servidor;

import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.pessoa.PessoaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ServidorTemporarioResponse(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate stDataAdmissao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate stDataDemissao,
        PessoaResponse pessoaResponse
){
}
