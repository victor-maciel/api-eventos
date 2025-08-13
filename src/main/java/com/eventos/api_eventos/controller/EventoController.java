package com.eventos.api_eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eventos.api_eventos.domain.evento.Evento;
import com.eventos.api_eventos.domain.evento.EventoRequestDTO;
import com.eventos.api_eventos.domain.evento.EventoResponseDTO;
import com.eventos.api_eventos.services.EventoService;

@RestController
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Evento> criar(@RequestParam("titulo") String titulo,
                                        @RequestParam(value = "descricao", required = false) String descricao,
                                        @RequestParam("data") Long data,
                                        @RequestParam("cidade") String cidade,
                                        @RequestParam("rua") String rua,
                                        @RequestParam("cep") String cep,
                                        @RequestParam("uf") String uf,
                                        @RequestParam("numero") String numero,
                                        @RequestParam("presencial") Boolean presencial,
                                        @RequestParam("eventoUrl") String eventoUrl,
                                        @RequestParam(value = "imagem", required = false) MultipartFile imagem) {
        EventoRequestDTO eventoRequestDTO = new EventoRequestDTO(titulo, descricao, data, cidade, rua, cep, uf, numero, presencial, eventoUrl, imagem);
        Evento novoEvento = this.eventoService.criarEvento(eventoRequestDTO);
        return ResponseEntity.ok(novoEvento);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> getEventos(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size){
        List<EventoResponseDTO> eventos = this.eventoService.getEventosPendentes(page, size);
        return  ResponseEntity.ok(eventos);
        
    }

}
