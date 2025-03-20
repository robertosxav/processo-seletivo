package br.com.robertoxavier.dto.pessoa;

import java.time.LocalDate;
import java.util.Set;

public record PessoaRequest(
         String pesNome,

         LocalDate pesDataNascimento,

         String pesSexo,

         String pesMae,

         String pesPai,

         Set<Long>enderecoIdList
) {
}
