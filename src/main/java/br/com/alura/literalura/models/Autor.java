package br.com.alura.literalura.models;

import br.com.alura.literalura.dtos.AutorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor() {}

    public Autor(AutorDTO autor) {
        this.nome = autor.nome();
        this.anoNascimento = autor.anoNascimento();
        this.anoFalecimento = autor.anoFalecimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        List<String> nomesLivros = livros.stream()
                .map(l -> l.getTitulo())
                .collect(Collectors.toList());
        return "---------------\n" +
                "Autor: " + nome + "\n"
                + "Ano de nascimento: " + anoNascimento + "\n"
                + "Ano de falecimento: " + anoFalecimento + "\n"
                + "Livros: " + nomesLivros + "\n" +
                "---------------";
    }
}