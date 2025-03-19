package br.com.robertoxavier.dto.unidade;

import br.com.robertoxavier.dto.endereco.EnderecoResponse;

import java.util.Set;

public record UnidadeResponse(
        Long unidId,
        String unidNome,
        String unidSigla,
        Set<EnderecoResponse>enderecoList
){
}
