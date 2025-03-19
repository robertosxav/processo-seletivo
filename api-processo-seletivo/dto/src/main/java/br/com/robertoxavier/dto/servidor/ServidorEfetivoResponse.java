package br.com.robertoxavier.dto.servidor;

import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.pessoa.PessoaResponse;

public record ServidorEfetivoResponse(
    String seMatricula,
    PessoaResponse pessoaResponse
){
}
