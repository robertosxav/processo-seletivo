package br.com.robertoxavier.dto.unidade;

import java.util.List;
import java.util.Set;

public record UnidadeRequest(
        String unidNome,
        String unidSigla,
        Set<Long> enderecoIdList
) {
}
