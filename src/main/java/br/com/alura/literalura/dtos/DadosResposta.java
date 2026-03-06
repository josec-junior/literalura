package br.com.alura.literalura.dtos;

import java.util.List;

public record DadosResposta(
        List<LivroDTO> resultados
) {
}