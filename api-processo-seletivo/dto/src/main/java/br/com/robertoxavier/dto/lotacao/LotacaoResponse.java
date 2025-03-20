package br.com.robertoxavier.dto.lotacao;

import br.com.robertoxavier.dto.pessoa.PessoaResponse;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;

import java.time.LocalDate;

public record LotacaoResponse(
        Long lotId,

        LocalDate lotDataLotacao,

        LocalDate lotDataRemocao,

        String lotPortaria,

        PessoaResponse pessoa,

        UnidadeResponse unidadeResponse
) {
}
