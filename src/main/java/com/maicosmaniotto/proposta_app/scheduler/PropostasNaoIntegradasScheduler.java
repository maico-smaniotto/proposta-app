package com.maicosmaniotto.proposta_app.scheduler;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maicosmaniotto.proposta_app.entity.Proposta;
import com.maicosmaniotto.proposta_app.repository.PropostaRepository;
import com.maicosmaniotto.proposta_app.service.NotificacaoService;

@Component
public class PropostasNaoIntegradasScheduler {

    private final PropostaRepository propostaRepository;
    private final NotificacaoService notificacaoService;
    private final String exchange;

    public PropostasNaoIntegradasScheduler(
        PropostaRepository propostaRepository, 
        NotificacaoService notificacaoService,
        @Value("${rabbitmq.propostapendente.exchange}") String exchange
    ) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    private void processarPropostasNaoIntegradas() {
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificacaoService.notificar(proposta, exchange);
                atualizarPropostaIntegrada(proposta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void atualizarPropostaIntegrada(Proposta proposta) {
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }

}
