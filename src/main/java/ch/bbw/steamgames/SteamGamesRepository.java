package ch.bbw.steamgames;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SteamGamesRepository extends JpaRepository<Game, Integer> {

    // SELECT mit min. 2 WHERE
    // SELECT mit Limit (bestenfalls mit Spring 'paging')
    // DELETE mit WHERE
    // SELECT mit JOIN der 2 Tabellen
    // komplizierte abfrage, die nur mit @Query möglich ist
    // SQL-injection anfällige methode

}
