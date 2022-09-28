package ch.bbw.steamgames.review;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ReviewTest implements WithAssertions {
    @Autowired
    ReviewRepository repository;

    @Test
    void findAllByGame_AppId() {
        List<Review> reviews = repository.findAllByGame_AppId(10);

        for(Review r : reviews) {
            assertThat(r.getId()).isEqualTo(10);
        }
    }
}
