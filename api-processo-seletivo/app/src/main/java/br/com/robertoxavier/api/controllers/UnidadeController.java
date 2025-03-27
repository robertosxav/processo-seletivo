package br.com.robertoxavier.api.controllers;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.unidade.UnidadeMapper;
import br.com.robertoxavier.dto.unidade.UnidadeRequest;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;
import br.com.robertoxavier.model.UnidadeModel;
import br.com.robertoxavier.stories.unidade.UnidadeUseStory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Unidades")
@RequestMapping("/unidade")
public class UnidadeController {

    private final UnidadeMapper unidadeMapper;
    private final UnidadeUseStory unidadeUseStory;

    public UnidadeController(UnidadeMapper unidadeMapper,
                             UnidadeUseStory unidadeUseStory) {
        this.unidadeMapper = unidadeMapper;
        this.unidadeUseStory = unidadeUseStory;
    }

    @Operation(
            summary = "Criar uma nova unidade",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo",
                                    value = """
                    {
                      "unidNome": "Departamento de Tecnologia",
                      "unidSigla": "DTI",
                      "enderecoIdList": [3,4]
                    }
                    """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação criada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada")
    })
    @PostMapping()
    public UnidadeResponse criarUnidade(@RequestBody UnidadeRequest unidadeRequest) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .criar(unidadeMapper.unidadeRequestToModel(unidadeRequest)));
    }

    @Operation(
            summary = "Atualizar uma unidade pelo Id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo",
                                    value = """
                   {
                      "unidNome": "Departamento de Tecnologia II",
                      "unidSigla": "DTI",
                      "enderecoIdList": [4,5]
                    }
                   """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidade atualizada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{unidId}")
    public UnidadeResponse atualizarUnidade(@PathVariable Long unidId,
                                          @RequestBody UnidadeRequest unidadeRequest) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .atualizar(unidId,unidadeMapper.unidadeRequestToModel(unidadeRequest)));
    }

    @Operation(summary = "Excluir uma unidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidade excluida com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{unidId}")
    public ResponseEntity<String> excluir(@PathVariable Long unidId) {
        unidadeUseStory.excluir(unidId);
        return ResponseEntity.ok("Unidade excluida com sucesso");
    }

    @Operation(summary = "Buscar uma unidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidade buscada pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/{unidId}")
    public UnidadeResponse buscarUnidadePorId(@PathVariable Long unidId) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .buscarPorId(unidId));
    }

    @Operation(summary = "Listar unidaes de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidades listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<UnidadeResponse> listaUnidadesPaginado( @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<UnidadeModel> unidadePage = unidadeUseStory.listaUnidadesPaginado(pageQuery);

        return unidadePage.map(unidadeMapper::unidadeModelToResponse);
    }
}