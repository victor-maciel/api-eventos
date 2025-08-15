package com.eventos.api_eventos.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventos.api_eventos.domain.evento.Evento;

public interface EventoRepository extends JpaRepository<Evento, UUID> {

    @Query("SELECT e FROM Evento e LEFT JOIN FETCH e.endereco a WHERE e.date >= :dataAtual")
    public Page<Evento> findEventosPendentes(@Param("dataAtual") Date dataAtual, Pageable pageable);

    @Query("SELECT e FROM Evento e LEFT JOIN e.endereco a "
            + "WHERE (:titulo = '' OR e.titulo LIKE %:titulo%) "
            + "AND (:cidade = '' OR a.cidade LIKE %:cidade%) "
            + "AND (:uf = '' OR a.uf LIKE %:uf%)" )
    Page<Evento> buscarEventosFiltrados(
            @Param("titulo") String titulo,
            @Param("cidade") String cidade,
            @Param("uf") String uf,
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim,
            Pageable pageable);

}
