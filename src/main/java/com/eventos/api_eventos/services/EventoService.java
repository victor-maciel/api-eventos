package com.eventos.api_eventos.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.eventos.api_eventos.domain.evento.Evento;
import com.eventos.api_eventos.domain.evento.EventoRequestDTO;

@Service
public class EventoService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3client;

    public Evento criarEvento(EventoRequestDTO data) {
        String imgUrl = null;

        if (data.imagem() != null) {
            this.uploadImg(data.imagem());
        }

        Evento novoEvento = new Evento();
        novoEvento.setTitulo(data.titulo());
        novoEvento.setDescricao(data.descricao());
        novoEvento.setEventoUrl(data.eventoUrl());
        novoEvento.setData(new Date(data.data()));
        novoEvento.setImgUrl(imgUrl);

        return novoEvento;

    }

    private String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            File file  = this.convertMultipartToFile(multipartFile);
            s3client.putObject(bucketName, fileName, file);
            file.delete();
            return s3client.getUrl(bucketName, fileName).toString();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";

    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException{

        File convFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        }
        return convFile;
    }


}
