package com.maicosmaniotto.proposta_app.dto;

public record PropostaRequest(
    String nome,
    String sobrenome,
    String cpf,
    String telefone,
    Double renda,
    Double valorSolicitado,
    int prazoPagamento
) { }