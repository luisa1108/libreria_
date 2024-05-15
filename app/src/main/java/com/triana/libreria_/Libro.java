package com.triana.libreria_;
import java.io.Serializable;

public class Libro implements Serializable{
    private String titulo;
    private String autor;
    private String imagen; // URL de la imagen del libro
    private String urlLectura;

    public Libro(String titulo, String autor, String imagen, String urlLectura) {
        this.titulo = titulo;
        this.autor = autor;
        this.imagen = imagen;
        this.urlLectura = urlLectura;
    }

    // Getters y setters para cada campo
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public void setUrlLectura(String urlLectura) {this.urlLectura = urlLectura;}

    public String getUrlLectura() {return urlLectura;}

}
