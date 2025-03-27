package br.com.robertoxavier.api.controllers;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.api.mappers.fotoPessoa.FotoMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.robertoxavier.dto.endereco.EnderecoResponse;
import br.com.robertoxavier.dto.fotoPessoa.FotoRequest;
import br.com.robertoxavier.dto.fotoPessoa.FotoResponse;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoLotacaoResponse;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoResponse;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import br.com.robertoxavier.service.Resource;
import br.com.robertoxavier.stories.endereco.EnderecoUseStory;
import br.com.robertoxavier.stories.fotoPessoa.FotoPessoaUseStory;
import br.com.robertoxavier.stories.servidor.ServidorEfetivoUseStory;
import br.com.robertoxavier.util.HashingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Servidores Efetivos")
@RequestMapping("/servidor-efetivo")
public class ServidorEfetivoController {

    private final ServidorEfetivoMapper servidorEfetivoMapper;

    private final ServidorEfetivoUseStory servidorEfetivoUseStory;

    private final FotoMapper fotoMapper;

    private final FotoPessoaUseStory fotoPessoaUseStory;

    private final EnderecoUseStory enderecoUseStory;

    private final EnderecoMapper enderecoMapper;

    public ServidorEfetivoController(ServidorEfetivoMapper servidorEfetivoMapper,
                                     ServidorEfetivoUseStory servidorEfetivoUseStory,
                                     FotoMapper fotoMapper, FotoPessoaUseStory fotoPessoaUseStory, EnderecoUseStory enderecoUseStory, EnderecoMapper enderecoMapper
    ) {
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.servidorEfetivoUseStory = servidorEfetivoUseStory;
        this.fotoMapper = fotoMapper;
        this.fotoPessoaUseStory = fotoPessoaUseStory;
        this.enderecoUseStory = enderecoUseStory;
        this.enderecoMapper = enderecoMapper;
    }

    @Operation(
            summary = "Criar um novo Servidor Efetivo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ServidorEfetivoRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo",
                                    value = """
                    {
                      "seMatricula": "123456",
                      "pessoaRequest": {
                        "pesNome": "João Silva",
                        "pesDataNascimento": "15/06/1985",
                        "pesSexo": "Masculino",
                        "pesMae": "Maria da Silva",
                        "pesPai": "José da Silva",
                        "enderecoIdList": [2,4]
                      }
                    }
                    """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidor efetivo criado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada")
    })
    @PostMapping()
    public ServidorEfetivoResponse criarServidorEfetivo(
            @RequestBody ServidorEfetivoRequest servidorEfetivoRequest) {
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .criar(servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));

    }

    @Operation(
            summary = "Atualizar um servidor efetivo pelo Id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ServidorEfetivoRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo",
                                    value = """
                    {
                      "seMatricula": "123456",
                      "pessoaRequest": {
                        "pesNome": "João Silva Sauro",
                        "pesDataNascimento": "15/06/1984",
                        "pesSexo": "Masculino",
                        "pesMae": "Maria da Silva Sauro",
                        "pesPai": "José da Silva Sauro",
                        "enderecoIdList": [2,3]
                      }
                    }
                    """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidor efetivo atualizado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{pesId}")
    public ServidorEfetivoResponse atualizarServidorEfetivo(@PathVariable Long pesId,
                                                            @RequestBody ServidorEfetivoRequest servidorEfetivoRequest)
    {
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .atualizar(pesId,servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));
    }

    @Operation(summary = "Fazer upload de fotos de um servidor efetivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Upload de fotos doServidor efetivo enviado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
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

    @Operation(summary = "Excluir uma Servidor efetivo pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servido efetivo excluido com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{pesId}")
    public ResponseEntity<String> excluir(@PathVariable Long pesId) {
        servidorEfetivoUseStory.excluir(pesId);
        return ResponseEntity.ok("Servidor Efetivo excluido com sucesso");
    }

    @Operation(summary = "Buscar um servidor efetivo pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidor efetivo buscado pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })

    @GetMapping("/{pesId}")
    public ServidorEfetivoResponse buscarServidorEfetivoPorId(@PathVariable Long pesId) {
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .buscarPorId(pesId));
    }

    @Operation(summary = "Listar servidores efetivos de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidores efetivos listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<ServidorEfetivoResponse>servidorEfetivoUseStory(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<ServidorEfetivoModel> servidorEfetivoPage = servidorEfetivoUseStory
                .listaServidoresEfetivosPaginado(pageQuery);

        return servidorEfetivoPage.map(servidorEfetivoMapper::servidorEfetivoModelToResponse);
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

    @Operation(summary = "Consultar os servidores efetivos lotados em determinada unidade parametrizando a consulta pelo atributo unid_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidores efetivos listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/lotados-unidade/{unidId}")
    public PageResponse<ServidorEfetivoLotacaoResponse> servidoresLotadosUnidade(@PathVariable Long unidId,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<ServidorEfetivoModel> paginado = servidorEfetivoUseStory
                .buscarServidoreLotadosUnidade(unidId,pageQuery);

        return paginado.map(servidorEfetivoMapper::servidorEfetivLotacaoModelToResponse);
    }

    @Operation(summary = "consultar o endereço funcional (da unidade onde o servidor é lotado) a partir de uma parte do nome do servidor efetivo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidores efetivos listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/endereco-funcional")
    public PageResponse<EnderecoResponse> enderecoFuncional(@RequestParam String nome,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<EnderecoModel> paginado = enderecoUseStory
                .buscarEnderecoFuncional(nome,pageQuery);

        return paginado.map(enderecoMapper::enderecoModelToResponse);
    }

}