package com.eventos.api_eventos.domain.endereco;

import java.util.UUID;

import com.eventos.api_eventos.domain.evento.Evento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "endereco") //nome da tabela
@Entity //indica que é uma entidade
@Getter //getter para cada campo informado
@Setter //setter para cada campo informado
@NoArgsConstructor //cria um construtor que recebe nenhum argumento
@AllArgsConstructor //cria um construtor que recebe todos os argumentos
public class Endereco {

    //id automatico gerada para cada interação no banco de dados
    @Id
    @GeneratedValue
    private UUID id;

    private String cidade;
    private String uf;
    private String rua;
    private String numero;
    private String cep;

    //Relação com a tabela evento
    @ManyToOne //um evento pode ter varios cupons e um cupom só pode pertencer há um evento
    @JoinColumn(name = "evento_id")
    private Evento evento;

}
