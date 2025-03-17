package br.com.robertoxavier.api.controllers;



import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.cidade.CidadeMapper;
import br.com.robertoxavier.dto.cidade.CidadeRequest;
import br.com.robertoxavier.dto.cidade.CidadeResponse;
import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.stories.cidade.CidadeUseStory;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    private final CidadeMapper cidadeMapper;
    private final CidadeUseStory cidadeUseStory;

    public CidadeController(CidadeMapper cidadeMapper,
                            CidadeUseStory cidadeUseStory) {
        this.cidadeMapper = cidadeMapper;
        this.cidadeUseStory = cidadeUseStory;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Criar uma nova cidade"),
    })
    @PostMapping()
    public CidadeResponse criarCidade(@RequestBody CidadeRequest cidadeRequest) {
        return cidadeMapper.cidadeModelToResponse(cidadeUseStory
                .criar(cidadeMapper.cidadeRequestToModel(cidadeRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Buscar uma cidade"),
    })
    @GetMapping("/{cidId}")
    public CidadeResponse buscarCidadePorId(@PathVariable Long cidId) {
        return cidadeMapper.cidadeModelToResponse(cidadeUseStory
                .buscarPorId(cidId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Listar todas cidades - paginado"),
    })
    @GetMapping("/paginado/all")
    public PageResponse<CidadeResponse> listaCidadesPaginado(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<CidadeModel> cidadePage = cidadeUseStory.listaCidadesPaginado(pageQuery);

        return cidadePage.map(cidadeMapper::cidadeModelToResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Atualizar uma cidade"),
    })
    @PutMapping("/{cidId}")
    public CidadeResponse atualizarCidade(@PathVariable Long cidId,
                                          @RequestBody CidadeRequest cidadeRequest) {
        return cidadeMapper.cidadeModelToResponse(cidadeUseStory
                .atualizar(cidId,cidadeMapper.cidadeRequestToModel(cidadeRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Excluir uma cidade"),
    })
    @DeleteMapping("/{cidId}")
    public ResponseEntity<String> excluir(@PathVariable Long cidId) {
        cidadeUseStory.excluir(cidId);
        return ResponseEntity.ok("Cidade excluida com sucesso");
    }
}