package br.com.robertoxavier.api.controllers;

import br.com.robertoxavier.PageQuery;

import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.lotacao.LotacaoMapper;
import br.com.robertoxavier.dto.lotacao.LotacaoRequest;
import br.com.robertoxavier.dto.lotacao.LotacaoResponse;
import br.com.robertoxavier.model.LotacaoModel;
import br.com.robertoxavier.stories.lotacao.LotacaoUseStory;
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
@Tag(name = "Lotações")
@RequestMapping("/lotacao")
public class LotacaoController {

    private final LotacaoMapper lotacaoMapper;
    private final LotacaoUseStory lotacaoUseStory;

    public LotacaoController(LotacaoMapper lotacaoMapper,
                             LotacaoUseStory lotacaoUseStory) {
        this.lotacaoMapper = lotacaoMapper;
        this.lotacaoUseStory = lotacaoUseStory;
    }

    @Operation(
            summary = "Criar uma nova lotação",
            description = "Este endpoint permite criar uma nova lotação com data formatada.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exemplo de payload para criar uma nova lotação",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LotacaoRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de LotacaoRequest",
                                    value = """
                    {
                      "lotDataLotacao": "26/03/2025",
                      "lotDataRemocao": "26/03/2025",
                      "lotPortaria": "123/2025",
                      "pesId": 1,
                      "unidId": 1
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
    public LotacaoResponse criarLotacao(@RequestBody LotacaoRequest lotacaoRequest) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .criar(lotacaoMapper.lotacaoRequestToModel(lotacaoRequest)));
    }

    @Operation(summary = "Atualizar uma lotação pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação atualizada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{lotId}")
    public LotacaoResponse atualizarLotacao(@PathVariable Long lotId,
                                          @RequestBody LotacaoRequest lotacaoRequest) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .atualizar(lotId,lotacaoMapper.lotacaoRequestToModel(lotacaoRequest)));
    }

    @Operation(summary = "Excluir uma lotação pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação excluida com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{lotId}")
    public ResponseEntity<String> excluir(@PathVariable Long lotId) {
        lotacaoUseStory.excluir(lotId);
        return ResponseEntity.ok("Lotacao excluida com sucesso");
    }

    @Operation(summary = "Buscar uma lotação pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação buscada pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    public LotacaoResponse buscarLotacaoPorId(@PathVariable Long lotId) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .buscarPorId(lotId));
    }

    @Operation(summary = "Listar lotações de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotações listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<LotacaoResponse> listaUnidadesPaginado(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<LotacaoModel> unidadePage = lotacaoUseStory.listaLotacoesPaginado(pageQuery);

        return unidadePage.map(lotacaoMapper::lotacaoModelToResponse);
    }
}