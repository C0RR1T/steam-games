package ch.bbw.steamgames.review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Integer> {

    List<Review> findAllByGame_AppId(Integer appId);
}
