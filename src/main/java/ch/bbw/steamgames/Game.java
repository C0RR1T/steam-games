package ch.bbw.steamgames;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    Integer appId;
    String name;
    LocalDate releaseDate;
    int english;
    String developer;
    String publisher;
    String platforms;
    int requiredAge;
    @Column(length = 500)
    String categories;
    String genres;
    String steamspyTags;
    int achievements;
    int positiveRatings;
    int negativeRatings;
    int averagePlaytime;
    int medianPlaytime;
    String owners;
    double price;
}
