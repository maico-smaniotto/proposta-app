package com.maicosmaniotto.proposta_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maicosmaniotto.proposta_app.dto.PropostaRequest;
import com.maicosmaniotto.proposta_app.dto.PropostaResponse;
import com.maicosmaniotto.proposta_app.entity.Proposta;

import java.text.NumberFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PropostaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", constant = "true")
    @Mapping(target = "observacao", ignore = true)
    Proposta toEntity(PropostaRequest proposta);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(getValorSolicitadoFmt(proposta))")
    PropostaResponse toResponse(Proposta proposta);

    List<PropostaResponse> toListResponse(Iterable<Proposta> propostas);

    default String getValorSolicitadoFmt(Proposta proposta) {
        return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
    }

}
