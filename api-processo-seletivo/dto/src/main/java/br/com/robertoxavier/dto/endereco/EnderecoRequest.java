package br.com.robertoxavier.dto.endereco;

import br.com.robertoxavier.dto.cidade.CidadeCompletoRequest;
import br.com.robertoxavier.dto.cidade.CidadeRequest;

public record EnderecoRequest(

        String endTipoLogradouro,

        String endLogradouro,

        Integer endNumero,

        String endBairro,

        CidadeCompletoRequest cidadeCompletoRequest
) {
}
