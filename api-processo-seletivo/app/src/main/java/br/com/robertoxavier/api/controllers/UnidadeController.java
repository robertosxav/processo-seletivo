package br.com.robertoxavier.api.controllers;



import br.com.robertoxavier.Direction;
import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.unidade.UnidadeMapper;
import br.com.robertoxavier.dto.unidade.UnidadeRequest;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;
import br.com.robertoxavier.model.UnidadeModel;
import br.com.robertoxavier.stories.unidade.UnidadeUseStory;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    private final UnidadeMapper unidadeMapper;
    private final UnidadeUseStory unidadeUseStory;

    public UnidadeController(UnidadeMapper unidadeMapper,
                             UnidadeUseStory unidadeUseStory) {
        this.unidadeMapper = unidadeMapper;
        this.unidadeUseStory = unidadeUseStory;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Criar uma nova unidade"),
    })
    @PostMapping()
    public UnidadeResponse criarUnidade(@RequestBody UnidadeRequest unidadeRequest) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .criar(unidadeMapper.unidadeRequestToModel(unidadeRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Buscar uma unidade"),
    })
    @GetMapping("/{unidId}")
    public UnidadeResponse buscarUnidadePorId(@PathVariable Long unidId) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .buscarPorId(unidId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Listar todas unidade - paginado"),
    })
    @GetMapping("/paginado/all")
    public PageResponse<UnidadeResponse> listaUnidadesPaginado( @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<UnidadeModel> unidadePage = unidadeUseStory.listaUnidadesPaginado(pageQuery);

        return unidadePage.map(unidadeMapper::unidadeModelToResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Atualizar uma unidade"),
    })
    @PutMapping("/{unidId}")
    public UnidadeResponse atualizarUnidade(@PathVariable Long unidId,
                                          @RequestBody UnidadeRequest unidadeRequest) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .atualizar(unidId,unidadeMapper.unidadeRequestToModel(unidadeRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Excluir uma unidade"),
    })
    @DeleteMapping("/{unidId}")
    public ResponseEntity<String> excluir(@PathVariable Long unidId) {
        unidadeUseStory.excluir(unidId);
        return ResponseEntity.ok("Unidade excluida com sucesso");
    }
}