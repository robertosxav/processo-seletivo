package br.com.robertoxavier.dto.pessoa;

import br.com.robertoxavier.dto.endereco.EnderecoResponse;

import java.time.LocalDate;
import java.util.Set;

public record PessoaResponse(
        Long pesId,

        String pesNome,

        LocalDate pesDataNascimento,

        String pesSexo,

        String pesMae,

        String pesPai,

        Set<EnderecoResponse> enderecoList
){
}
