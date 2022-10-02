package ch.bbw.steamgames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private GameRepository repository;

    @Autowired
    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Optional<Game> getById(int appId) {
        return repository.findById(appId);
    }
}
