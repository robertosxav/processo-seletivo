package br.com.robertoxavier.api.controllers;



import br.com.robertoxavier.PageQuery;

import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.lotacao.LotacaoMapper;
import br.com.robertoxavier.dto.lotacao.LotacaoRequest;
import br.com.robertoxavier.dto.lotacao.LotacaoResponse;
import br.com.robertoxavier.model.LotacaoModel;
import br.com.robertoxavier.stories.lotacao.LotacaoUseStory;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lotacao")
public class LotacaoController {

    private final LotacaoMapper lotacaoMapper;
    private final LotacaoUseStory lotacaoUseStory;

    public LotacaoController(LotacaoMapper lotacaoMapper,
                             LotacaoUseStory lotacaoUseStory) {
        this.lotacaoMapper = lotacaoMapper;
        this.lotacaoUseStory = lotacaoUseStory;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Criar uma nova lotacao"),
    })
    @PostMapping()
    public LotacaoResponse criarLotacao(@RequestBody LotacaoRequest lotacaoRequest) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .criar(lotacaoMapper.lotacaoRequestToModel(lotacaoRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Buscar uma lotacao"),
    })
    @GetMapping("/{lotId}")
    public LotacaoResponse buscarLotacaoPorId(@PathVariable Long lotId) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .buscarPorId(lotId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Listar todas lotacoes - paginado"),
    })
    @GetMapping("/paginado/all")
    public PageResponse<LotacaoResponse> listaUnidadesPaginado(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<LotacaoModel> unidadePage = lotacaoUseStory.listaLotacoesPaginado(pageQuery);

        return unidadePage.map(lotacaoMapper::lotacaoModelToResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Atualizar uma lotacao"),
    })
    @PutMapping("/{lotId}")
    public LotacaoResponse atualizarUnidade(@PathVariable Long lotId,
                                          @RequestBody LotacaoRequest lotacaoRequest) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .atualizar(lotId,lotacaoMapper.lotacaoRequestToModel(lotacaoRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Excluir uma lotacao"),
    })
    @DeleteMapping("/{lotId}")
    public ResponseEntity<String> excluir(@PathVariable Long lotId) {
        lotacaoUseStory.excluir(lotId);
        return ResponseEntity.ok("Lotacao excluida com sucesso");
    }
}