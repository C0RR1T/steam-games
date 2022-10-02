package ch.bbw.steamgames.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository repository;
    private EntityManager em;

    @Autowired
    public ReviewService(ReviewRepository repository, EntityManager em) {
        this.repository = repository;
        this.em = em;
    }

    public List<Review> getAllReviews(int from, int size) {
        Pageable pageable = PageRequest.of(from, size);

        return repository.findAll(pageable).toList();
    }

    public List<Review> getAllByGame(Integer game) {
        return repository.findAllByGame(game);
    }

    public List<TopAndFlopGames> getTopAndFlopGames() {
        var games = repository.getTopGames();
        games.addAll(repository.getFlopGames());
        return games;
    }

    public void deleteById(int game) {
        repository.deleteReviewByGame_AppId(game);
    }


    // OOOPS
    public List accidentalSQLInjection(String name) {
        return em.createNativeQuery("SELECT review_text FROM review  WHERE game_app_id = " + name).getResultList();
    }
}
