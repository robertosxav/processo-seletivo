package br.com.robertoxavier.api.controllers;


import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.fotoPessoa.FotoMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoResponse;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import br.com.robertoxavier.service.Resource;
import br.com.robertoxavier.stories.fotoPessoa.FotoPessoaUseStory;
import br.com.robertoxavier.stories.servidor.ServidorEfetivoUseStory;
import br.com.robertoxavier.util.HashingUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/servidor-efetivo")
public class ServidorEfetivoController {

    private final ServidorEfetivoMapper servidorEfetivoMapper;

    private final ServidorEfetivoUseStory servidorEfetivoUseStory;

    private final FotoMapper fotoMapper;

    private final FotoPessoaUseStory fotoPessoaUseStory;

    public ServidorEfetivoController(ServidorEfetivoMapper servidorEfetivoMapper, ServidorEfetivoUseStory servidorEfetivoUseStory, FotoMapper fotoMapper, FotoPessoaUseStory fotoPessoaUseStory) {
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.servidorEfetivoUseStory = servidorEfetivoUseStory;
        this.fotoMapper = fotoMapper;
        this.fotoPessoaUseStory = fotoPessoaUseStory;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Criar um servidor Efetivo"),
    })

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ServidorEfetivoResponse criarServidorEfetivo(
            @RequestParam(name = "matricula", required = false) String matricula,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "dataNascimento", required = false) LocalDate dataNascimento,
            @RequestParam(name = "sexo", required = false) String sexo,
            @RequestParam(name = "nomeMae", required = false) String nomeMae,
            @RequestParam(name = "nomePai", required = false) String nomePai,
            @RequestParam(name = "listaEnderecosId", required = false) Set<Long> listaEnderecosId,
            @RequestParam(name = "fotos", required = false) List<MultipartFile> fotos
    ) {
        List<Resource> listaResource = fotos.stream().map(this::resourceOf).toList();

        PessoaRequest pessoaRequest = new PessoaRequest(
                nome,dataNascimento,sexo,nomeMae,nomePai,listaEnderecosId);
        ServidorEfetivoRequest servidorEfetivoRequest = new ServidorEfetivoRequest(
                matricula,pessoaRequest);
        ServidorEfetivoResponse servidorEfetivoResponse =  servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .criar(servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));

        /*FotoResponse fotoResponse =  fotoMapper.fotoModelToResponse(servidorEfetivoUseStory
                .criar(fotoMapper.fotoRequestToModel(servidorEfetivoRequest)));*/
        return servidorEfetivoResponse;
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
            @ApiResponse(responseCode  = "200", description  = "Atualizar uma lotacao"),
    })
    @PutMapping("/{pesId}")
    public ServidorEfetivoResponse atualizarUnidade(@PathVariable Long pesId,
                                                       @RequestParam(name = "matricula", required = false) String matricula,
                                                       @RequestParam(name = "nome", required = false) String nome,
                                                       @RequestParam(name = "dataNascimento", required = false) LocalDate dataNascimento,
                                                       @RequestParam(name = "sexo", required = false) String sexo,
                                                       @RequestParam(name = "nomeMae", required = false) String nomeMae,
                                                       @RequestParam(name = "nomePai", required = false) String nomePai,
                                                       @RequestParam(name = "listaEnderecosId", required = false) Set<Long> listaEnderecosId
                                                       //    @RequestParam(name = "fotos", required = false) List<MultipartFile> fotos
    ) {
        //List<Resource> listaResource = fotos.stream().map(this::resourceOf).toList();

        PessoaRequest pessoaRequest = new PessoaRequest(
                nome,dataNascimento,sexo,nomeMae,nomePai,listaEnderecosId);
        ServidorEfetivoRequest servidorEfetivoRequest = new ServidorEfetivoRequest(
                matricula,pessoaRequest);
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .atualizar(pesId,servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Buscar um servidor efetivo"),
    })
    @GetMapping("/{pesId}")
    public ServidorEfetivoResponse buscarCidadePorId(@PathVariable Long pesId) {
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .buscarPorId(pesId));
    }

    @GetMapping("/paginado/all")
    public PageResponse<ServidorEfetivoResponse>servidorEfetivoUseStory(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<ServidorEfetivoModel> unidadePage = servidorEfetivoUseStory.listaServidoresEfetivosPaginado(pageQuery);

        return unidadePage.map(servidorEfetivoMapper::servidorEfetivoModelToResponse);
    }
}