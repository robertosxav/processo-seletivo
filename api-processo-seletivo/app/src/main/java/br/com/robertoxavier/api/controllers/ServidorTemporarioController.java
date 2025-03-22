package br.com.robertoxavier.api.controllers;



import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.fotoPessoa.FotoMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorTemporarioMapper;
import br.com.robertoxavier.dto.fotoPessoa.FotoRequest;
import br.com.robertoxavier.dto.fotoPessoa.FotoResponse;
import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.servidor.ServidorTemporarioRequest;
import br.com.robertoxavier.dto.servidor.ServidorTemporarioResponse;
import br.com.robertoxavier.model.ServidorTemporarioModel;
import br.com.robertoxavier.service.Resource;
import br.com.robertoxavier.stories.fotoPessoa.FotoPessoaUseStory;
import br.com.robertoxavier.stories.servidor.ServidorTemporarioUseStory;
import br.com.robertoxavier.util.HashingUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/servidor-temporario")
public class ServidorTemporarioController {

    private final ServidorTemporarioMapper servidorTemporarioMapper;

    private final ServidorTemporarioUseStory servidorTemporarioUseStory;

    private final FotoMapper fotoMapper;

    private final FotoPessoaUseStory fotoPessoaUseStory;

    public ServidorTemporarioController(ServidorTemporarioMapper servidorTemporarioMapper, ServidorTemporarioUseStory servidorTemporarioUseStory, FotoMapper fotoMapper, FotoPessoaUseStory fotoPessoaUseStory) {
        this.servidorTemporarioMapper = servidorTemporarioMapper;
        this.servidorTemporarioUseStory = servidorTemporarioUseStory;
        this.fotoMapper = fotoMapper;
        this.fotoPessoaUseStory = fotoPessoaUseStory;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Criar um servidor Efetivo"),
    })

    @PostMapping()
    public ServidorTemporarioResponse criarServidorTemporario(
            @RequestParam(name = "dataAdmissao", required = false) LocalDate dataAdmissao,
            @RequestBody ServidorTemporarioRequest servidorTemporarioRequest
    ) {

        return  servidorTemporarioMapper.servidorTemporarioModelToResponse(servidorTemporarioUseStory
                .criar(servidorTemporarioMapper.servidorTemporarioRequestToModel(servidorTemporarioRequest)));

    }

    private Resource resourceOf(final MultipartFile part) {
        if (part == null) {
            return null;
        }

        try {
            return Resource.with(
                    part.getBytes(),
                    HashingUtils.checksum(part.getBytes()),
                    part.getContentType(),
                    part.getOriginalFilename()
            );
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Buscar um servidor efetivo"),
    })
    @GetMapping("/{pesId}")
    public ServidorTemporarioResponse buscarCidadePorId(@PathVariable Long pesId) {
        return servidorTemporarioMapper.servidorTemporarioModelToResponse(servidorTemporarioUseStory
                .buscarPorId(pesId));
    }

    @GetMapping("/paginado/all")
    public PageResponse<ServidorTemporarioResponse> servidorTemporarioUseStory(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<ServidorTemporarioModel> unidadePage = servidorTemporarioUseStory.listaServidoresEfetivosPaginado(pageQuery);

        return unidadePage.map(servidorTemporarioMapper::servidorTemporarioModelToResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Atualizar uma lotacao"),
    })
    @PutMapping("/{pesId}")
    public ServidorTemporarioResponse atualizarServidorTemporario(@PathVariable Long pesId,
                                                                  @RequestBody ServidorTemporarioRequest servidorTemporarioRequest
                                                          ) {

        return servidorTemporarioMapper.servidorTemporarioModelToResponse(servidorTemporarioUseStory
                .atualizar(pesId,servidorTemporarioMapper.servidorTemporarioRequestToModel(servidorTemporarioRequest)));
    }
    @PostMapping(value = "/upload-fotos/{pesId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public List<FotoResponse> uploadFotos(
            @PathVariable Long pesId,
            @RequestParam(name = "fotos", required = false) List<MultipartFile> fotos
    ){
        List<Resource> listaResource = fotos.stream().map(this::resourceOf).toList();
        List<FotoResponse>listaFotoResponse =new ArrayList<FotoResponse>();
        List<FotoRequest>listaFotoRequest =new ArrayList<FotoRequest>();

        listaResource.forEach((f)->{
            FotoRequest fotoRequest = new FotoRequest(pesId,f);
            listaFotoRequest.add(fotoRequest);
        });

        listaFotoResponse =  fotoMapper.fotoModelListToFotoResponseList(fotoPessoaUseStory
                .uploadFotos(fotoMapper.fotoRequestListToFotoModelList(listaFotoRequest)));


        return listaFotoResponse;
    }
}