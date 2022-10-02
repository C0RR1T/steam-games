package ch.bbw.steamgames.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Review> getAllReviewsPaged(@RequestParam("from") Integer from, @RequestParam("size") Integer size) {
        return service.getAllReviews(from, size);
    }

    @GetMapping("/reviews/{game}")
    public List<Review> getAllReviewsByGame(@PathVariable Integer game) {
        return service.getAllByGame(game);
    }

    @GetMapping("/reviews/177t/hacker-lul")
    public List getAllReviewsButHackerCool(@RequestBody String name) {
        return service.accidentalSQLInjection(name);
    }

    @GetMapping("/reviews/top-and-flop")
    public List<TopAndFlopGames> getTopAndFlopGames() {
        return service.getTopAndFlopGames();
    }

    @DeleteMapping("/reviews/{game}")
    public ResponseEntity<?> deleteByGame(@PathVariable int game) {
        service.deleteById(game);
        return ResponseEntity.ok().build();
    }
}
