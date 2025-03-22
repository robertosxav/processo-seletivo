package br.com.robertoxavier.api.controllers;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.dto.endereco.EnderecoRequest;
import br.com.robertoxavier.dto.endereco.EnderecoResponse;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.stories.endereco.EnderecoUseStory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Enderecos")
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoUseStory enderecoUseStory;

    public EnderecoController(EnderecoMapper enderecoMapper,
                              EnderecoUseStory enderecoUseStory) {
        this.enderecoMapper = enderecoMapper;
        this.enderecoUseStory = enderecoUseStory;
    }

    @Operation(summary = "Criar uma novo endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco criado com sucesso"),
            @ApiResponse(responseCode  = "500", description  = "Erro no servidor"),
    })
    @PostMapping()
    public EnderecoResponse criarEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .criar(enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }


    @Operation(summary = "Atualizar um endereco pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco atualizado com sucesso"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado"),
            @ApiResponse(responseCode  = "500", description  = "Erro no servidor"),
    })
    @PutMapping("/{endId}")
    public EnderecoResponse atualizarEndereco(@PathVariable Long endId,
                                          @RequestBody EnderecoRequest enderecoRequest) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .atualizar(endId,enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }

    @Operation(summary = "Excluir um endereco pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco excluido com sucesso"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado"),
            @ApiResponse(responseCode  = "500", description  = "Erro no servidor"),
    })
    @DeleteMapping("/{endId}")
    public ResponseEntity<String> excluir(@PathVariable Long endId) {
        enderecoUseStory.excluir(endId);
        return ResponseEntity.ok("Enedereco excluido com sucesso");
    }

    @Operation(summary = "Buscar um endereco pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco buscado pelo Id com sucesso"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado"),
            @ApiResponse(responseCode  = "500", description  = "Erro no servidor"),
    })
    @GetMapping("/{endId}")
    public EnderecoResponse buscarEnderecpPorId(@PathVariable Long endId) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .buscarPorId(endId));
    }

    @Operation(summary = "Listar enderecos de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Enderecos listados de forma paginado"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado"),
            @ApiResponse(responseCode  = "500", description  = "Erro no servidor"),
    })
    @GetMapping("/paginado/all")
    public PageResponse<EnderecoResponse> listaEnderecosPaginado(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<EnderecoModel> enderecoPage = enderecoUseStory.listaEnderecosPaginado(pageQuery);

        return enderecoPage.map(enderecoMapper::enderecoModelToResponse);
    }
}