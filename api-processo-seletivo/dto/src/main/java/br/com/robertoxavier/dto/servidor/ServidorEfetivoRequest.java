package br.com.robertoxavier.dto.servidor;

import br.com.robertoxavier.dto.pessoa.PessoaRequest;

public record ServidorEfetivoRequest(
        String seMatricula,
        PessoaRequest pessoaRequest
) {
}
