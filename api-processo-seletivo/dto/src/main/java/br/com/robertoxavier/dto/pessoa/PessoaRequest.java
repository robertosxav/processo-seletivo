package br.com.robertoxavier.dto.pessoa;

import br.com.robertoxavier.service.Resource;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record PessoaRequest(
         String pesNome,

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate pesDataNascimento,

         String pesSexo,

         String pesMae,

         String pesPai,

         Set<Long>enderecoIdList
) {
}
