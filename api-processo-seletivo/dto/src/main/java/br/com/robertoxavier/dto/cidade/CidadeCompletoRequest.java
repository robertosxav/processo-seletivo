package br.com.robertoxavier.dto.cidade;

public record CidadeCompletoRequest(
        Long cidId,
        String cidNome,
        String cidUf
) {
}
