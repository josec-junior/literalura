package br.com.alura.literalura.principal;

import br.com.alura.literalura.repositories.AutorRepository;
import br.com.alura.literalura.repositories.LivroRepository;
import br.com.alura.literalura.services.AutorService;
import br.com.alura.literalura.services.ConsumoAPI;
import br.com.alura.literalura.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private LivroService livroService;
    private AutorService autorService;

    public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.livroService = new LivroService(autorRepository, livroRepository);
        this.autorService = new AutorService(autorRepository, livroRepository);
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            System.out.println("------------\n" +
                    "1 - Buscar livro pelo título\n" +
                    "2 - Listar livros registrados\n" +
                    "3 - Listar autores registrados\n" +
                    "4 - Listar autores vivos em um determinado ano\n" +
                    "5 - Listar livros em um determinado idioma\n" +
                    "0 - Sair\n");
            System.out.print("Escolha o número da sua opção: ");
            opcao = leitura.nextInt();
            switch (opcao) {
                case 1:
                    livroService.buscarLivroPeloTitulo();
                    break;
                case 2:
                    livroService.listarLivrosRegistrados();
                    break;
                case 3:
                    autorService.listarAutoresRegistrados();
                    break;
                case 4:
                    autorService.listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    livroService.listarLivrosDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}