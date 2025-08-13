package com.eventos.api_eventos.domain.evento;

import org.springframework.web.multipart.MultipartFile;

public record EventoRequestDTO(String titulo, 
                                String descricao, 
                                Long data,
                                String cidade, 
                                String rua, 
                                String cep, 
                                String uf, 
                                String numero, 
                                boolean presencial, 
                                String eventoUrl, 
                                MultipartFile imagem ) {


}
