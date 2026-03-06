package br.com.alura.literalura.repositories;

import br.com.alura.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE :ano BETWEEN a.anoNascimento AND (a.anoFalecimento - 1) OR a.anoFalecimento IS NULL")
    List<Autor> buscarAutoresPorAno(int ano);
}