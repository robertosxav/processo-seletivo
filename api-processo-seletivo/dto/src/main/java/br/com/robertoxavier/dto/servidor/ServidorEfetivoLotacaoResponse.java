package br.com.robertoxavier.dto.servidor;

import br.com.robertoxavier.dto.pessoa.PessoaResponse;

public record ServidorEfetivoLotacaoResponse(
    String nome,
    Long idade,
    String nomeUnidade,
    String listaLinkFotos
){
}
