package br.com.robertoxavier.dto.servidor;

import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.pessoa.PessoaResponse;

import java.time.LocalDate;

public record ServidorTemporarioResponse(
        LocalDate stDataAdmissao,
        LocalDate stDataDemissao,
        PessoaResponse pessoaResponse
){
}
