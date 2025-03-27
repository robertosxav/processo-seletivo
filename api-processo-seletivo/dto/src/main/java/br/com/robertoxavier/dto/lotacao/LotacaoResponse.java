package br.com.robertoxavier.dto.lotacao;

import br.com.robertoxavier.dto.pessoa.PessoaResponse;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record LotacaoResponse(
        Long lotId,

        @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR")
        LocalDate lotDataLotacao,

        @JsonFormat(pattern = "dd/MM/yyyy",locale = "pt-BR")
        LocalDate lotDataRemocao,

        String lotPortaria,

        PessoaResponse pessoa,

        UnidadeResponse unidadeResponse
) {
}
