package br.com.alura.literalura.services;

import br.com.alura.literalura.models.Autor;
import br.com.alura.literalura.repositories.AutorRepository;
import br.com.alura.literalura.repositories.LivroRepository;

import java.util.List;
import java.util.Scanner;

public class AutorService {
    private Scanner leitura = new Scanner(System.in);
    private AutorRepository autorRepository;
    private LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nennhum autor registado!");
        } else {
            System.out.println("--- Autores Registrados ---");
            autores.forEach(System.out::println);
        }
    }

    public void listarAutoresVivosEmDeterminadoAno() {
        System.out.print("Insira o ano que você deseja pesquisar: ");
        var ano = leitura.nextInt();
        List<Autor> autores = autorRepository.buscarAutoresPorAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Não há registros de autores vivos no ano de " + ano);
        } else {
            System.out.println("Autores que estavam vivos no ano de " + ano);
            autores.forEach(System.out::println);
        }
    }
}