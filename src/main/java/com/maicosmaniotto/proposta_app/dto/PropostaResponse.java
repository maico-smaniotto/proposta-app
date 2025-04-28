package com.maicosmaniotto.proposta_app.dto;

public record PropostaResponse(
    Long id,
    String nome,
    String sobrenome,
    String cpf,
    String telefone,
    Double renda,
    Double valorSolicitado,
    int prazoPagamento,
    Boolean aprovada,
    boolean integrada,
    String observacao
) { }
