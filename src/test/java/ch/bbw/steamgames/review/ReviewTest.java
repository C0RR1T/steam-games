package ch.bbw.steamgames.review;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class ReviewTest implements WithAssertions {
    @Autowired
    ReviewRepository repository;
    @Autowired
    ReviewService service;

    @Test
    void findAllByGame_AppId() {
        List<Review> reviews = repository.findAllByGame(10);

        for (Review r : reviews) {
            assertThat(r.getGame().getAppId()).isEqualTo(10);
        }
        assertThat(reviews.size()).isEqualTo(6);
    }

    @Test
    void deleteGame() {
        repository.deleteReviewByGame_AppId(10);

        List<Review> reviews = repository.findAllByGame(10);

        assertThat(reviews).isEmpty();
    }

    @Test
    void topAndFlopGames() {
        var topAndFlop = service.getTopAndFlopGames();
        assertThat(topAndFlop.size()).isEqualTo(10);
        assertThat(topAndFlop.get(0).getScore()).isEqualTo("12");
    }

    @Test
    void paged() {
        var pageResult = repository.findAll(Pageable.ofSize(10)).stream().toList();
        assertThat(pageResult.size()).isEqualTo(10L);
    }

    @Test
    void hackerLulTest() {
        List<String> out = (List<String>) service.accidentalSQLInjection("10;DELETE FROM reviews");
        assertThat(repository.count()).isEqualTo(0);
    }

}
