package com.eventos.api_eventos.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventos.api_eventos.domain.cupom.Cupom;

public interface CupomRepository extends JpaRepository<Cupom, UUID> {

    List<Cupom> findByEventoIdAndValidadeAfter(UUID eventoId, Date dataAtual);

}

