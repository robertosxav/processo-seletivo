package br.com.robertoxavier.dto.cidade;

import javax.validation.constraints.Size;
public record CidadeRequest(
        String cidNome,
        String cidUf
) {
}
