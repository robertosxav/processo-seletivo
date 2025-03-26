package br.com.robertoxavier.api.controllers;


import br.com.robertoxavier.service.Resource;
import br.com.robertoxavier.service.StorageService;
import br.com.robertoxavier.util.HashingUtils;
import io.minio.http.Method;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/minio")
public class TesteMinIOController {

    private final StorageService storage;

    public TesteMinIOController(StorageService storage) {
        this.storage = storage;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "upload de arquivo"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam final String id, @RequestParam("media") MultipartFile media) {
        // Armazenar o arquivo recebido
        storage.store(id, resourceOf(media));

        return ResponseEntity
                .ok()
                .body("ok");

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> get(@PathVariable String id){
        var body = storage.get(id);
        if (body.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o recurso não for encontrado
        }
       return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, body.get().contentType()) // Definindo o tipo de conteúdo no header
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + body.get().name()) // Definindo o nome do arquivo
                .body(body.get().content());
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

    @GetMapping("/link-temporario/{id}")
    public String generateTemporaryLink(@PathVariable String id) {
        return storage.generateTemporaryLink(id);
    }

    @DeleteMapping("")
    public ResponseEntity<String> excluir(@RequestParam List<String> ids){
       storage.deleteAll(ids);
        return ResponseEntity.ok()
                .body("Arquivos excluidos");
    }
}