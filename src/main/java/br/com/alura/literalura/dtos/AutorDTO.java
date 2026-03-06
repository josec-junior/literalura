package br.com.alura.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDTO(
        @JsonAlias("name")
        String nome,
        @JsonAlias("birth_year")
        Integer anoNascimento,
        @JsonAlias("death_year")
        Integer anoFalecimento
) {
}