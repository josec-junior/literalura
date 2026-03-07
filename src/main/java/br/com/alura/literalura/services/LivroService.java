package br.com.alura.literalura.services;

import br.com.alura.literalura.dtos.AutorDTO;
import br.com.alura.literalura.dtos.DadosResposta;
import br.com.alura.literalura.dtos.LivroDTO;
import br.com.alura.literalura.models.Autor;
import br.com.alura.literalura.models.Livro;
import br.com.alura.literalura.repositories.AutorRepository;
import br.com.alura.literalura.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LivroService {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private AutorRepository autorRepository;
    private LivroRepository livroRepository;

    public LivroService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public void buscarLivroPeloTitulo() {
        System.out.print("Insira o nome do livro que você deseja buscar: ");
        var titulo = leitura.nextLine();
        String json = consumoAPI.obterDados(titulo);
        DadosResposta resposta = conversor.obterDados(json, DadosResposta.class);
        if (resposta.resultados().isEmpty()) {
            System.out.println("Livro não encontrado!");
        } else {
            LivroDTO dadosLivro = resposta.resultados().get(0);
            AutorDTO dadosAutor = dadosLivro.autores().get(0);
            Optional<Autor> autorOptional = autorRepository.findByNome(dadosAutor.nome());
            Optional<Livro> livroOptional = livroRepository.findByTitulo(dadosLivro.titulo());

            Autor autor;

            if (autorOptional.isPresent()) {
                autor = autorOptional.get();
            } else {
                Autor novoAutor = new Autor(dadosAutor);
                autor = autorRepository.save(novoAutor);
            }
            Livro livro;
            if (livroOptional.isEmpty()) {
                livro = new Livro(dadosLivro);
                livro.setAutor(autor);
                livroRepository.save(livro);
            } else {
                livro = livroOptional.get();
            }
            System.out.println(livro);
        }
    }

    public void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado!");
        } else {
            System.out.println("--- Livros Registrados ---");
            livros.forEach(System.out::println);
        }
    }

    public void listarLivrosDeterminadoIdioma() {
        System.out.println("en - Inglês\n" +
                "es - Espanhol\n" +
                "pt - Português\n" +
                "fr - Francês");
        System.out.print("Digite a sigla de qual idioma você deseja buscar os livros: ");
        var idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado!");
        } else {
            System.out.println("Foram encontrados " + livros.size() + " livros para o idioma informado");
            livros.forEach(System.out::println);
        }
    }

    public void listarTop10LivrosMaisBaixados() {
        List<Livro> livros = livroRepository.listarTop10LivrosMaisBaixados();
        if (livros.isEmpty()) {
            System.out.println("Ainda não há livros registrados no Banco de Dados!");
        } else {
            System.out.println("--- Top 10 Livros mais baixados ---");
            livros.forEach(liv -> System.out.println(liv.getTitulo() + " - Quantidade de Downloads: " + liv.getQuantidadeDownloads()));
        }
    }

    public void gerarEstatisticas() {
        List<Livro> livros = livroRepository.findAll();
        IntSummaryStatistics estatisticas = livros.stream()
                .filter(livro -> livro.getQuantidadeDownloads() > 0 || livro.getQuantidadeDownloads() != null)
                .mapToInt(Livro::getQuantidadeDownloads)
                .summaryStatistics();

        System.out.println(
                "Quantidade de Livros Registrados: " + estatisticas.getCount() +
                        "\nMédia de Downloads: " + estatisticas.getAverage() +
                        "\nQuantidade de Downloads do livro mais baixado: " + estatisticas.getMax() +
                        "\nQuantidade de Downloads do livro menos baixado: " + estatisticas.getMin());
    }
}