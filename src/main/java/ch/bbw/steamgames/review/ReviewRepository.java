package ch.bbw.steamgames.review;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Integer> {


    @Query("SELECT r FROM Review r WHERE r.game.appId = :game")
    List<Review> findAllByGame(@Param("game") Integer game);

    @Query(
            value = """
                    SELECT g.name as name, SUM(r.review_score) as score
                    FROM Review r
                    JOIN Game g
                    ON r.game_app_id = g.app_id
                    GROUP BY g.name
                    ORDER BY score DESC
                    LIMIT 5
                    """,
            nativeQuery = true
    )
    List<TopAndFlopGames> getTopGames();

    @Query(
            value = """
                    SELECT g.name as name, SUM(r.review_score) as score
                                        FROM Review r
                                        JOIN Game g
                                        ON r.game_app_id = g.app_id
                                        GROUP BY g.name
                                        ORDER BY score ASC
                                        LIMIT 5
                    """,
            nativeQuery = true
    )
    List<TopAndFlopGames> getFlopGames();

    @Transactional
    void deleteReviewByGame_AppId(int appId);
}
