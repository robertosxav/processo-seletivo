package br.com.robertoxavier.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LotacaoRequest(

         @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR")
         LocalDate lotDataLotacao,

         @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR")
         LocalDate lotDataRemocao,

         String lotPortaria,

         Long pesId,

         Long unidId
         ) {
    
}
