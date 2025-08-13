package com.eventos.api_eventos.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventos.api_eventos.domain.evento.Evento;

public interface EventoRepository extends JpaRepository<Evento, UUID> {

}

