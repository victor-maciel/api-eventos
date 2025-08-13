package com.eventos.api_eventos.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventos.api_eventos.domain.endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

}

