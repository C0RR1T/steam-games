package ch.bbw.steamgames;

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
    Integer appId;
    String name;
    LocalDate releaseDate;
    Integer english;
    String developer;
    String publisher;
    String platforms;
    Integer requiredAge;
    @Column(length = 500)
    String categories;
    String genres;
    String steamspyTags;
    Integer achievements;
    Integer positiveRatings;
    Integer negativeRatings;
    Integer averagePlaytime;
    Integer medianPlaytime;
    String owners;
    Double price;
}
