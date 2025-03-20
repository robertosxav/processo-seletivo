package br.com.robertoxavier.dto.pessoa;

import br.com.robertoxavier.service.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
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
