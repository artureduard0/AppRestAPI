package com.example.consumindorestapi;

import java.util.Date;

public class Posts {
    private int id;
    private String title;
    private String excerpt;
    private String author;
    private int comments_total;

    public Posts(int id, String title, String excerpt, String author, int comments_total) {
        // Cria o objeto removendo partes do HTML que são retornadas pela API
        this.id = id;
        if(title.contains(";")){
            title = title.substring(10);
        }
        this.title = title;
        String aux = excerpt.replace("<p>","");
        aux = aux.replace("[&hellip;]","");
        aux = aux.replace("</p>","");
        this.excerpt = aux;
        this.author = author;
        this.comments_total = comments_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getComments_total() {
        return comments_total;
    }

    public void setComments_total(int comments_total) {
        this.comments_total = comments_total;
    }

    @Override
    public String toString() {
        // Formato da lista a ser exibido na tela
        return "Post: "+ id +
                "\nTítulo: " + title +
                "\nConteúdo: " + excerpt +
                "\nAutor: " + author +
                "\nTotal de comentários: " + comments_total;
    }
}
