package com.maicosmaniotto.proposta_app.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.maicosmaniotto.proposta_app.dto.PropostaResponse;

@Service
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificar(PropostaResponse proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
