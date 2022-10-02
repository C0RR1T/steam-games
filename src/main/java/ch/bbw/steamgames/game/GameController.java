package ch.bbw.steamgames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {
    private GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable int id) {
        var game = service.getById(id);

        return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
