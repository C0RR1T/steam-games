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

    public List<Review> getAllReviews() {
        var reviews = new ArrayList<Review>();
        repository.findAll().forEach(reviews::add);
        return reviews;
    }

    public List<Review> getAllReviews(int from, int size) {
        Pageable pageable = PageRequest.of(from, size);

        return repository.findAll(pageable).toList();
    }

    public List<Review> getAllByGame(int game) {
        return repository.findAllByGame_AppId(game);
    }


    // OOOPS
    public List accidentalSQLInjection(String name) {
        return em.createNativeQuery("SELECT * FROM review WHERE 'name' = " + name).getResultList();
    }
}
