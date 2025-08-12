package com.eventos.api_eventos.domain.cupom;

import java.util.Date;
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

@Table(name = "cupom") //nome da tabela
@Entity //indica que é uma entidade
@Getter //getter para cada campo informado
@Setter //setter para cada campo informado
@NoArgsConstructor //cria um construtor que recebe nenhum argumento
@AllArgsConstructor //cria um construtor que recebe todos os argumentos
public class Cupom {

    //id automatico gerada para cada interação no banco de dados
    @Id
    @GeneratedValue
    private UUID id;

    private String codigo; //codigo do cupom
    private Integer desconto; //desconto
    private Date validade; // validade do cupom

    //Relação com a tabela evento
    @ManyToOne //um evento pode ter varios cupons e um cupom só pode pertencer há um evento
    @JoinColumn(name = "evento_id")
    private Evento evento;

}
