package com.eventos.api_eventos.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventos.api_eventos.domain.cupom.Cupom;
import com.eventos.api_eventos.domain.cupom.CupomRequestDTO;
import com.eventos.api_eventos.domain.evento.Evento;
import com.eventos.api_eventos.repositories.CupomRepository;
import com.eventos.api_eventos.repositories.EventoRepository;

@Service
public class CupomService {

    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Cupom adicionarCupomEvento(UUID eventoId, CupomRequestDTO data){
        Evento evento = eventoRepository.findById(eventoId).orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        Cupom cupom = new Cupom();
        cupom.setCodigo(data.codigo());
        cupom.setDesconto(data.desconto());
        cupom.setValidade(new Date(data.validade()));
        cupom.setEvento(evento);

        return  cupomRepository.save(cupom);
        
    }

    public List<Cupom> consultaCupons(UUID eventoId, Date dataAtual) {
        return cupomRepository.findByEventoIdAndValidadeAfter(eventoId, dataAtual);
    }

}
