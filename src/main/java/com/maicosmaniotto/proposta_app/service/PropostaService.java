package com.maicosmaniotto.proposta_app.service;

import com.maicosmaniotto.proposta_app.mapper.PropostaMapper;
import com.maicosmaniotto.proposta_app.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import com.maicosmaniotto.proposta_app.dto.PropostaRequest;
import com.maicosmaniotto.proposta_app.dto.PropostaResponse;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public PropostaResponse criarProposta(PropostaRequest propostaRequest) {

        var proposta = PropostaMapper.INSTANCE.toEntity(propostaRequest);
        proposta = propostaRepository.save(proposta);

        return PropostaMapper.INSTANCE.toResponse(proposta);
    }
}
