package br.com.tdmxp.controller;

import br.com.tdmxp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService service;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarEmail(
            @RequestParam String destinatario,
            @RequestParam String assunto,
            @RequestParam String mensagem) {


            service.enviarEmail(destinatario, assunto, mensagem);
            return ResponseEntity.ok("E-mail enviado com sucesso para " + destinatario);


    }
}