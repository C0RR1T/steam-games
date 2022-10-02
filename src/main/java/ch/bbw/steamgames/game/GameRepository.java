package ch.bbw.steamgames.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    // SELECT mit min. 2 WHERE
    List<Game> findAllByGenresIsInOrCategoriesIsIn(List<String> genresFilter, List<String> categoriesFilter);


}
