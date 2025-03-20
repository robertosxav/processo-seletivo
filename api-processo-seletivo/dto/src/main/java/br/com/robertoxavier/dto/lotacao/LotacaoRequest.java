package br.com.robertoxavier.dto.lotacao;

import java.time.LocalDate;

public record LotacaoRequest(

         LocalDate lotDataLotacao,

         LocalDate lotDataRemocao,

         String lotPortaria,

         Long pesId,

         Long unidId
         ) {
    
}
