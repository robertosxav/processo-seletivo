package br.com.robertoxavier.dto.servidor;

import br.com.robertoxavier.dto.pessoa.PessoaResponse;

import java.util.Set;

public record ServidorEfetivoLotacaoResponse(
    String nome,
    Long idade,
    String nomeUnidade,
    Set<String> listaLinkFotos
){
}
