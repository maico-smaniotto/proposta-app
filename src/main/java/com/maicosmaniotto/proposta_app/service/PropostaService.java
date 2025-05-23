package com.maicosmaniotto.proposta_app.service;

import com.maicosmaniotto.proposta_app.mapper.PropostaMapper;
import com.maicosmaniotto.proposta_app.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.maicosmaniotto.proposta_app.dto.PropostaRequest;
import com.maicosmaniotto.proposta_app.dto.PropostaResponse;
import com.maicosmaniotto.proposta_app.entity.Proposta;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;
    private final PropostaMapper propostaMapper;
    private final NotificacaoService notificacaoService;
    private final String exchange;

    public PropostaService(
        PropostaRepository propostaRepository, 
        PropostaMapper propostaMapper, 
        NotificacaoService notificacaoService,
        @Value("${rabbitmq.propostapendente.exchange}") String exchange
    ) {
        this.propostaRepository = propostaRepository;
        this.propostaMapper = propostaMapper;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    public PropostaResponse criar(PropostaRequest propostaRequest) {
        var proposta = propostaMapper.toEntity(propostaRequest);
        proposta = propostaRepository.save(proposta);
    
        notificar(proposta);
     
        return propostaMapper.toResponse(proposta);
    }

    public List<PropostaResponse> listar() {
        return propostaMapper.toListResponse(propostaRepository.findAll());
    }

    private void notificar(Proposta proposta) {
        try {
            notificacaoService.notificar(proposta, exchange);
        } catch (Exception e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }
}
