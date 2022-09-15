package com.api.desafio.dtos;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class PublicationDto {

    @NotBlank
    private String title;

    @NotBlank
    private String conteudo;

    private UUID author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }


}
