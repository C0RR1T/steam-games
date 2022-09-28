package ch.bbw.steamgames.review;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByGame_AppId(Integer appId);
}
