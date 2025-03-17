package br.com.robertoxavier.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servidor-efetivo")
public class ServidorEfetivoController {

    @GetMapping("")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Serviço teste de servidor efetivo");
    }
}