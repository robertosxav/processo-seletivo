package br.com.robertoxavier.dto.lotacao;

import java.time.LocalDate;

public record LotacaoResponse(
        Long lotId,

        LocalDate lotDataLotacao,

        LocalDate lotDataRemocao,

        Long pesId,

        Long unidId
) {
}
