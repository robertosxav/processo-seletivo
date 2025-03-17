package br.com.robertoxavier.api.controllers;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servidor-temporario")
public class ServidorTemporarioController {

    @GetMapping("")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Serviço teste de servidor temporario");
    }
}