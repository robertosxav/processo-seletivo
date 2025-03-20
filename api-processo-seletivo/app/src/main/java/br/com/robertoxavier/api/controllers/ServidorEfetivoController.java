package br.com.robertoxavier.api.controllers;


import br.com.robertoxavier.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoResponse;
import br.com.robertoxavier.stories.servidor.ServidorEfetivoUseStory;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    public ServidorEfetivoController(ServidorEfetivoMapper servidorEfetivoMapper, ServidorEfetivoUseStory servidorEfetivoUseStory) {
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.servidorEfetivoUseStory = servidorEfetivoUseStory;
    }

    @GetMapping("")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Serviço teste de servidor efetivo");
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
        PessoaRequest pessoaRequest = new PessoaRequest(
                nome,dataNascimento,sexo,nomeMae,nomePai,listaEnderecosId);
        ServidorEfetivoRequest servidorEfetivoRequest = new ServidorEfetivoRequest(
                matricula,pessoaRequest);
        return servidorEfetivoMapper.servidorModelToResponse(servidorEfetivoUseStory
                .criar(servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));


    }
}