package br.com.robertoxavier.dto.lotacao;

import br.com.robertoxavier.model.PessoaModel;
import br.com.robertoxavier.model.UnidadeModel;

import java.time.LocalDate;

public record LotacaoRequest(

         LocalDate lotDataLotacao,

         LocalDate lotDataRemocao,

         Long pesId,

         Long unidId
         ) {
    
}
