package ch.bbw.steamgames.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }

    @GetMapping(params = {"from", "size"})
    public List<Review> getAllReviewsPaged(@RequestParam("from") int from, @RequestParam("size") int size) {
        return service.getAllReviews(from, size);
    }

    @GetMapping("/{game}")
    public List<Review> getAllReviewsByGame(@PathVariable int game) {
        return service.getAllByGame(game);
    }

    @GetMapping("/177t/hacker-lul")
    public List<Review> getAllReviewsButHackerCool(@RequestBody String name) {
        return service.accidentalSQLInjection(name);
    }
}
