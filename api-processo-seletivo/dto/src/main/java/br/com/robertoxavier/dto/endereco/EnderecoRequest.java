package br.com.robertoxavier.dto.endereco;

public record EnderecoRequest(
        
         String endTipoLogradouro,

         String endLogradouro,

         Integer endNumero,

         String endBairro,

         Long cidId
) {
}
