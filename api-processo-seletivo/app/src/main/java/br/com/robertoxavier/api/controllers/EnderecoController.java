package br.com.robertoxavier.api.controllers;

import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.dto.endereco.EnderecoRequest;
import br.com.robertoxavier.dto.endereco.EnderecoResponse;
import br.com.robertoxavier.stories.endereco.EnderecoUseStory;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoUseStory enderecoUseStory;

    public EnderecoController(EnderecoMapper enderecoMapper,
                              EnderecoUseStory enderecoUseStory) {
        this.enderecoMapper = enderecoMapper;
        this.enderecoUseStory = enderecoUseStory;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Criar um novo endereco"),
    })
    @PostMapping()
    public EnderecoResponse criarEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .criar(enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Buscar um endereco"),
    })
    @GetMapping("/{endId}")
    public EnderecoResponse buscarEnderecpPorId(@PathVariable Long endId) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .buscarPorId(endId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Listar todos enderecos - paginado"),
    })
    @GetMapping("/paginado/all")
    public List<EnderecoResponse> listaCidadesPaginado() {
        return enderecoMapper.enderecoModelListToEnderecoResponseList(enderecoUseStory
                .listaCidadesPaginado());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Atualizar um endereco"),
    })
    @PutMapping("/{endId}")
    public EnderecoResponse atualizarCidade(@PathVariable Long endId,
                                          @RequestBody EnderecoRequest enderecoRequest) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .atualizar(endId,enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }

   /* @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Excluir um endereco"),
    })
    @DeleteMapping("/{endId}")
    public ResponseEntity<String> excluir(@PathVariable Long endId) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .buscarPorId(endId));
    }*/
}