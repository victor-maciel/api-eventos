package com.eventos.api_eventos.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.eventos.api_eventos.domain.cupom.Cupom;
import com.eventos.api_eventos.domain.evento.Evento;
import com.eventos.api_eventos.domain.evento.EventoDetalhesDTO;
import com.eventos.api_eventos.domain.evento.EventoRequestDTO;
import com.eventos.api_eventos.domain.evento.EventoResponseDTO;
import com.eventos.api_eventos.repositories.EventoRepository;

@Service
public class EventoService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CupomService cupomService;

    public Evento criarEvento(EventoRequestDTO data) {
        String imgUrl = null;

        if (data.imagem() != null) {
            imgUrl = this.uploadImg(data.imagem());
        }

        Evento novoEvento = new Evento();
        novoEvento.setTitulo(data.titulo());
        novoEvento.setDescricao(data.descricao());
        novoEvento.setEventoUrl(data.eventoUrl());
        novoEvento.setDate(new Date(data.data()));
        novoEvento.setImgUrl(imgUrl);
        novoEvento.setPresencial(data.presencial());

        eventoRepository.save(novoEvento);

        //se o evento for presencial, criar persistir o endereço do parametro
        if (data.presencial()) {
            this.enderecoService.criarEndereco(data, novoEvento);
        }

        return novoEvento;

    }

    //upar imagem no bucket da amazon s3
    private String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            File file = this.convertMultipartToFile(multipartFile);
            s3client.putObject(bucketName, fileName, file);
            file.delete();
            return s3client.getUrl(bucketName, fileName).toString();

        } catch (Exception e) {
            System.out.println("erro ao subir o arquivo");
            return "";
        }

    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {

        File convFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        }
        return convFile;
    }

    //todos os eventos
    public List<EventoResponseDTO> getEventos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Evento> paginacao = this.eventoRepository.findAll(pageable);

        return paginacao.map(evento -> new EventoResponseDTO(evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDate(),
                evento.getEndereco() != null ? evento.getEndereco().getCidade() : "",
                evento.getEndereco() != null ? evento.getEndereco().getRua() : "",
                evento.getEndereco() != null ? evento.getEndereco().getCep() : "",
                evento.getEndereco() != null ? evento.getEndereco().getUf() : "",
                evento.getEndereco() != null ? evento.getEndereco().getNumero() : "",
                evento.getPresencial(),
                evento.getEventoUrl(),
                evento.getImgUrl())).stream().toList();

    }

    //eventos pendentes
    public List<EventoResponseDTO> getEventosPendentes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Evento> paginacao = this.eventoRepository.findEventosPendentes(new Date(), pageable);

        return paginacao.map(evento -> new EventoResponseDTO(evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDate(),
                evento.getEndereco() != null ? evento.getEndereco().getCidade() : "",
                evento.getEndereco() != null ? evento.getEndereco().getRua() : "",
                evento.getEndereco() != null ? evento.getEndereco().getCep() : "",
                evento.getEndereco() != null ? evento.getEndereco().getUf() : "",
                evento.getEndereco() != null ? evento.getEndereco().getNumero() : "",
                evento.getPresencial(),
                evento.getEventoUrl(),
                evento.getImgUrl())).stream().toList();
    }

    public List<EventoResponseDTO> getEventosFiltrados(int page, int size, String titulo, String cidade, String uf,
            Date dataInicio, Date dataFim) {

        titulo = (titulo != null) ? titulo : "";
        cidade = (cidade != null) ? cidade : "";
        uf = (uf != null) ? uf : "";

        Pageable pageable = PageRequest.of(page, size);
        Page<Evento> paginacao = this.eventoRepository.buscarEventosFiltrados(titulo, cidade, uf, dataInicio, dataFim, pageable);
        return paginacao.map(evento -> new EventoResponseDTO(evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDate(),
                evento.getEndereco() != null ? evento.getEndereco().getCidade() : "",
                evento.getEndereco() != null ? evento.getEndereco().getRua() : "",
                evento.getEndereco() != null ? evento.getEndereco().getCep() : "",
                evento.getEndereco() != null ? evento.getEndereco().getUf() : "",
                evento.getEndereco() != null ? evento.getEndereco().getNumero() : "",
                evento.getPresencial(),
                evento.getEventoUrl(),
                evento.getImgUrl())).stream().toList();
    }

    public EventoDetalhesDTO getEventoDetalhes(UUID eventId) {
        Evento evento = eventoRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        List<Cupom> cupons = cupomService.consultaCupons(eventId, new Date());

        List<EventoDetalhesDTO.CupomDTO> cupomDTOs = cupons.stream()
                .map(cupom -> new EventoDetalhesDTO.CupomDTO(
                cupom.getCodigo(),
                cupom.getDesconto(),
                cupom.getValidade()))
                .collect(Collectors.toList());

        return new EventoDetalhesDTO(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDate(),
                evento.getEndereco() != null ? evento.getEndereco().getCidade() : "",
                evento.getEndereco() != null ? evento.getEndereco().getRua() : "",
                evento.getEndereco() != null ? evento.getEndereco().getCep() : "",
                evento.getEndereco() != null ? evento.getEndereco().getUf() : "",
                evento.getEndereco() != null ? evento.getEndereco().getNumero() : "",
                evento.getPresencial(),
                evento.getEventoUrl(),
                evento.getImgUrl(),
                cupomDTOs);
    }

}
