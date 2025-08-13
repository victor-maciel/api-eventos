package com.eventos.api_eventos.domain.evento;

import java.util.Date;
import java.util.UUID;

public record EventoResponseDTO(UUID id,
                                String titulo, 
                                String descricao, 
                                Date data,
                                String cidade, 
                                String rua, 
                                String cep, 
                                String uf, 
                                String numero, 
                                boolean presencial, 
                                String eventoUrl, 
                                String imgUrl) {

}
