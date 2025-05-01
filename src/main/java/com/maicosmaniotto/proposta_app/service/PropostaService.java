package com.maicosmaniotto.proposta_app.service;

import com.maicosmaniotto.proposta_app.mapper.PropostaMapper;
import com.maicosmaniotto.proposta_app.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import com.maicosmaniotto.proposta_app.dto.PropostaRequest;
import com.maicosmaniotto.proposta_app.dto.PropostaResponse;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;
    private final PropostaMapper propostaMapper;

    public PropostaService(PropostaRepository propostaRepository, PropostaMapper propostaMapper) {
        this.propostaRepository = propostaRepository;
        this.propostaMapper = propostaMapper;
    }

    public PropostaResponse criar(PropostaRequest propostaRequest) {

        var proposta = propostaMapper.toEntity(propostaRequest);
        proposta = propostaRepository.save(proposta);

        return propostaMapper.toResponse(proposta);
    }
}
