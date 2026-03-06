package br.com.alura.literalura.principal;

import br.com.alura.literalura.services.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner leitura = new Scanner(System.in);

    public void exibirMenu() {
        System.out.print("Qual livro ou autor você deseja buscar? ");
        var busca = leitura.nextLine();
        var resultado = consumoAPI.obterDados(busca);
        System.out.println(resultado);
    }
}