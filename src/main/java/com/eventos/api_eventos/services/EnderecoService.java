package com.eventos.api_eventos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventos.api_eventos.domain.endereco.Endereco;
import com.eventos.api_eventos.domain.evento.Evento;
import com.eventos.api_eventos.domain.evento.EventoRequestDTO;
import com.eventos.api_eventos.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    //criar o endereço
    public Endereco criarEndereco(EventoRequestDTO data, Evento evento) {

        Endereco endereco = new Endereco();
        endereco.setCidade(data.cidade());
        endereco.setUf(data.uf());
        endereco.setRua(data.rua());
        endereco.setNumero(data.numero());
        endereco.setCep(data.cep());
        endereco.setEvento(evento);

        return enderecoRepository.save(endereco);

    }

}
