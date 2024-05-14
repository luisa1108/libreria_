package com.triana.libreria_;

public class Libro {
    private String titulo;
    private String autor;
    private String imagen; // URL de la imagen del libro

    public Libro(String titulo, String autor, String imagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.imagen = imagen;
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
}
