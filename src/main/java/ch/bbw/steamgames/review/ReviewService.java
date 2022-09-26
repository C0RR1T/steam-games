package ch.bbw.steamgames.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    ReviewRepository repository;

    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
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
}
