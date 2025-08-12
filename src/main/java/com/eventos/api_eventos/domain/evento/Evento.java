package com.eventos.api_eventos.domain.evento;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "evento")
@Entity
@Getter //getter para cada campo informado
@Setter //setter para cada campo informado
@NoArgsConstructor //cria um construtor que recebe nenhum argumento
@AllArgsConstructor //cria um construtor que recebe todos os argumentos
public class Evento {

    //id automatico gerada para cada interação no banco de dados
    @Id
    @GeneratedValue
    private UUID id;

    //titulo do evento
    private String titulo;
    //descrição do evento
    private String descricao;
    //url da imagem do evento (banner)
    private String imgUrl;
    //url da pagina do evento
    private String eventoUrl;
    //indica se é remoto ou nao
    private Boolean remoto;
    //data do evento
    private Date data;


}
