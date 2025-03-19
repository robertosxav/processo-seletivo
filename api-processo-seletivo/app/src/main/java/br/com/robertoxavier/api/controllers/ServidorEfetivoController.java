package br.com.robertoxavier.api.controllers;


import br.com.robertoxavier.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoResponse;
import br.com.robertoxavier.stories.servidor.ServidorEfetivoUseStory;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping()
    public ServidorEfetivoResponse criarServidorEfetivo(@RequestBody ServidorEfetivoRequest servidorEfetivoRequest) {
        return servidorEfetivoMapper.servidorModelToResponse(servidorEfetivoUseStory
                .criar(servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));

    }
}