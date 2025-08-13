package com.eventos.api_eventos.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventos.api_eventos.domain.cupom.Cupom;
import com.eventos.api_eventos.domain.cupom.CupomRequestDTO;
import com.eventos.api_eventos.services.CupomService;

@RestController
@RequestMapping("/api/cupom")
public class CupomController {

    @Autowired
    private CupomService cupomService;

    @PostMapping("/evento/{eventoId}")
    public ResponseEntity<Cupom> adicionarCupomEvento(@PathVariable UUID eventoId, @RequestBody CupomRequestDTO data){

        Cupom cupons = cupomService.adicionarCupomEvento(eventoId, data);
        return ResponseEntity.ok(cupons);

    }

}
