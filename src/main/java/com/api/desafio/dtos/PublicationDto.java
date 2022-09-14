package com.api.desafio.dtos;



import javax.validation.constraints.NotBlank;

public class PublicationDto {

    @NotBlank
    private String title;

    @NotBlank
    private String conteudo;

    @NotBlank
    private String author;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
