package br.com.robertoxavier.dto.endereco;

import br.com.robertoxavier.dto.cidade.CidadeResponse;

public record EnderecoResponse(

         Long endId,
        
         String endTipoLogradouro,

         String endLogradouro,

         Integer endNumero,

         String endBairro,

         CidadeResponse cidadeResponse
) {
}
