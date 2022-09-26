package ch.bbw.steamgames.game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    // SELECT mit min. 2 WHERE
    List<Game> findAllByGenresIsInAndCategoriesIsIn(List<String> genresFilter, List<String> categoriesFilter);

    // SELECT mit Limit (bestenfalls mit Spring 'paging')
    // DELETE mit WHERE
    // SELECT mit JOIN der 2 Tabellen
    // komplizierte abfrage, die nur mit @Query möglich ist
    // SQL-injection anfällige methode

}
