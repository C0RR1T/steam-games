package ch.bbw.steamgames;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;


public record Game(@Id Integer appId, String name, LocalDate releaseDate, int english, String developer, String publisher, String platforms, int requiredAge, String categories, String genres, String steamspyTags, int achievements, int positiveRatings, int negativeRatings, int averagePlaytime, int medianPlaytime, String owners, double price) {
}
