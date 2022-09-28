package ch.bbw.steamgames.game;

import ch.bbw.steamgames.review.Review;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameTest implements WithAssertions {

    @Autowired
    GameRepository repository;

    @Test
    void findAllByGenresIsInAndCategoriesIsIn() {
        List<Game> games = repository.findAllByGenresIsInAndCategoriesIsIn("Multi-player", "Action");

        for(Game g : games) {
            assertThat(g.getGenres()).contains("Multi-player");
            assertThat(g.getCategories()).contains("Action");
        }
    }
}
