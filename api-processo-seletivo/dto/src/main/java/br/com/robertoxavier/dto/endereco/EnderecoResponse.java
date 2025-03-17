package br.com.robertoxavier.dto.endereco;

public record EnderecoResponse(

         Long endId,
        
         String endTipoLogradouro,

         String endLogradouro,

         Integer endNumero,

         String endBairro,
         
         Long cidId
) {
}
