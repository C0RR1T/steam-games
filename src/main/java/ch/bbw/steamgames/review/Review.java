package ch.bbw.steamgames.review;

import ch.bbw.steamgames.game.Game;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private int id;
    @ManyToOne
    private Game game;
    @Column(length = 10_000)
    private String reviewText;
    private Integer reviewScore;
    private Integer reviewVotes;

}
