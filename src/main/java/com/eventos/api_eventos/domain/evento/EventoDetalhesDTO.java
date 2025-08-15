package com.eventos.api_eventos.domain.evento;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record EventoDetalhesDTO(UUID id,
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
        String imgUrl,
        List<CupomDTO> Cupons) {

    public record CupomDTO(String codigo, Integer desconto, Date validade) {

    }

}
