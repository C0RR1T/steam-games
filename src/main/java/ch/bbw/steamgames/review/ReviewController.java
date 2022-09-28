package ch.bbw.steamgames.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviewsPaged(@RequestParam("from") int from, @RequestParam("size") int size) {
        return service.getAllReviews(from, size);
    }

    @GetMapping("/reviews/{game}")
    public List<Review> getAllReviewsByGame(@PathVariable int game) {
        return service.getAllByGame(game);
    }

    @GetMapping("/reviews/177t/hacker-lul")
    public List<Review> getAllReviewsButHackerCool(@RequestBody String name) {
        return service.accidentalSQLInjection(name);
    }
}
