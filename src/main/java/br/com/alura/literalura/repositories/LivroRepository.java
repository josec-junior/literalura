package br.com.alura.literalura.repositories;

import br.com.alura.literalura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

    List<Livro> findByIdioma(String idioma);

    @Query("SELECT l FROM Livro l ORDER BY l.quantidadeDownloads DESC LIMIT 10")
    List<Livro> listarTop10LivrosMaisBaixados();
}
