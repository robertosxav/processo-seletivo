package br.com.robertoxavier.dto.servidor;

import br.com.robertoxavier.dto.pessoa.PessoaRequest;

import java.time.LocalDate;

public record ServidorTemporarioRequest(
        LocalDate stDataAdmissao,
        LocalDate stDataDemissao,
        PessoaRequest pessoaRequest
) {
}
