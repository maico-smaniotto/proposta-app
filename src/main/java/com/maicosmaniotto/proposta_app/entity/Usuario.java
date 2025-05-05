package com.maicosmaniotto.proposta_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private double renda;

    // O mapeamento é feito pela Proposta, que tem o id_usuario no atributo "usuario"
    @OneToOne(mappedBy = "usuario")
    @JsonBackReference
    private Proposta proposta;

}
