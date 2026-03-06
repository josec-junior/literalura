package br.com.alura.literalura.models;

import br.com.alura.literalura.dtos.LivroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private Integer quantidadeDownloads;

    public Livro() {}

    public Livro(LivroDTO livro) {
        this.titulo = livro.titulo();
        this.idioma = livro.idiomas().get(0);
        this.quantidadeDownloads = livro.quantidadeDownloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getQuantidadeDownloads() {
        return quantidadeDownloads;
    }

    public void setQuantidadeDownloads(Integer quantidadeDownloads) {
        this.quantidadeDownloads = quantidadeDownloads;
    }

    @Override
    public String toString() {
        return "----- LIVRO ----" + "\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autor.getNome() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Quantidade de Downloads: " + quantidadeDownloads + "\n" +
                "----------";
    }
}