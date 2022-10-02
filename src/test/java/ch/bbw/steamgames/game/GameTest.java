package ch.bbw.steamgames.game;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GameTest implements WithAssertions {

    @Autowired
    GameRepository repository;

    @Test
    void findAllByGenresIsInAndCategoriesIsIn() {
        List<Game> games = repository.findAllByGenresIsInOrCategoriesIsIn(List.of("Multi-player"), List.of("Action"));

        for(Game g : games) {
            assertThat(g.getGenres()).contains("Multi-player");
            assertThat(g.getCategories()).contains("Action");
        }
    }
}
