package com.maicosmaniotto.proposta_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maicosmaniotto.proposta_app.dto.PropostaRequest;
import com.maicosmaniotto.proposta_app.dto.PropostaResponse;
import com.maicosmaniotto.proposta_app.service.PropostaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping
    public ResponseEntity<PropostaResponse> criar(@RequestBody PropostaRequest propostaRequest) {
        PropostaResponse propostaResponse = propostaService.criar(propostaRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(propostaResponse.id())
                .toUri()).body(propostaResponse);
    }
}
