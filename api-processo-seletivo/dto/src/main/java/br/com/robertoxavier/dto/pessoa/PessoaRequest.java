package br.com.robertoxavier.dto.pessoa;

import java.time.LocalDate;

public record PessoaRequest(
         String pesNome,

         LocalDate pesDataNascimento,

         String pesSexo,

         String pesMae,

         String pesPai
) {
}
