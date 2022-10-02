package ch.bbw.steamgames.game;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    private Integer appId;
    private String name;
    private LocalDate releaseDate;
    private Integer english;
    private String developer;
    private String publisher;
    private String platforms;
    private Integer requiredAge;
    @Column(length = 500)
    private String categories;
    private String genres;
    private String steamspyTags;
    private Integer achievements;
    private Integer positiveRatings;
    private Integer negativeRatings;
    private Integer averagePlaytime;
    private Integer medianPlaytime;
    private String owners;
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Game))
            return false;
        Game g = (Game) o;
        System.out.println("Hello");
        return getAppId().equals(g.getAppId());
    }
}
