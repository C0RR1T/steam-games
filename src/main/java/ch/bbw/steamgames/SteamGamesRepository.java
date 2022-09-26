package ch.bbw.steamgames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SteamGamesRepository extends JpaRepository<Game, Integer> {

    // SELECT mit min. 2 WHERE
    @Query("SELECT e FROM Game e WHERE e.categories IN :filter AND e.genres IN :filter")
    Game findByCategoryAndGenre(@Param("filter") Integer rankFilter);

    // SELECT mit Limit (bestenfalls mit Spring 'paging')
    // DELETE mit WHERE
    // SELECT mit JOIN der 2 Tabellen
    // komplizierte abfrage, die nur mit @Query möglich ist
    // SQL-injection anfällige methode

}
